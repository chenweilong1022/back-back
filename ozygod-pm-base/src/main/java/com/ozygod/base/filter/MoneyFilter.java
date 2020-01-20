package com.ozygod.base.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.*;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.ozygod.base.annotations.MoneyField;
import com.ozygod.base.enums.MoneyFormatType;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-20 15:05
 */
@Slf4j
public class MoneyFilter implements ValueFilter {


    @Override
    public Object process(Object object, String name, Object value) {
        try {
            Field declaredField = object.getClass().getDeclaredField(name);
            MoneyField annotation = declaredField.getAnnotation(MoneyField.class);
            if (ObjectUtil.isNull(annotation) || ObjectUtil.isNull(value)) {
                return value;
            }
            /**
             * 格式化类型
             */
            MoneyFormatType[] moneyFormatTypes = annotation.moneyFormatTypes();
            if (ArrayUtil.isEmpty(moneyFormatTypes)) {
                return value;
            }
            /**
             * 格式化方式
             */
            String format = annotation.format();

            /**
             *  除以100
             */
            if (ArrayUtil.contains(moneyFormatTypes,MoneyFormatType.DIV)) {
                value = new BigDecimal(String.valueOf(value)).divide(BigDecimal.valueOf(100)).doubleValue();
            }

            /**
             *  格式化
             */
            if (ArrayUtil.contains(moneyFormatTypes,MoneyFormatType.FORMATTER) && StrUtil.isNotBlank(format)) {
                value = NumberUtil.decimalFormat(format,Convert.toDouble(value));
            }
        } catch (NoSuchFieldException e) {
            return value;
        }
        return value;
    }

}
