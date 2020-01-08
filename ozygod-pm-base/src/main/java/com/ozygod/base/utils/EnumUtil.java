package com.ozygod.base.utils;

import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.enums.BaseEnum;
import com.ozygod.base.enums.IndexCard;
import com.ozygod.base.vo.EnumVo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 枚举工具类
 */
public class EnumUtil {


    public static void main(String[] args) {
        System.out.println(enumToVo(IndexCard.values()));
    }

    public static String describe(BaseEnum[] baseEnums) {
        String describe = enumToVo(baseEnums).stream().map(enumVo -> enumVo.getKey() + ":" + enumVo.getValue() + " ,").collect(Collectors.joining());
        return describe;
    }


    public static List<EnumVo> enumToVo(BaseEnum[] baseEnums) {
        return Arrays.stream(baseEnums).map(EnumUtil::toEnumVo).collect(Collectors.toList());
    }

    public static List<String> values(BaseEnum[] baseEnums) {
        return enumToVo(baseEnums).stream().map(EnumVo::getValue).collect(Collectors.toList());
    }

    public static List<Integer> keys(BaseEnum[] baseEnums) {
        return enumToVo(baseEnums).stream().map(EnumVo::getKey).collect(Collectors.toList());
    }

    public static String queryValueByKey(Integer key, BaseEnum[] baseEnums) {
        BaseEnum baseEnum1 = Arrays.stream(baseEnums).filter(baseEnum -> baseEnum.getKey().equals(key)).findAny().orElse(null);
        return ObjectUtil.isNotNull(baseEnum1) ? baseEnum1.getValue() : null;
    }


    private static EnumVo toEnumVo(BaseEnum baseEnum) {
        return new EnumVo().setKey(baseEnum.getKey()).setValue(baseEnum.getValue());
    }

}
