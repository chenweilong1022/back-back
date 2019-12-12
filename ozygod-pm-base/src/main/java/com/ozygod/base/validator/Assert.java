package com.ozygod.base.validator;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.exception.RRException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 * 陈伟龙
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isTrue(boolean flag, String message) {
        if (flag) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (ObjectUtil.isNotNull(object)) {
            throw new RRException(message);
        }
    }

    public static<T> void isNullArray(T[] object, String message) {
        if (object == null || object.length == 0) {
            throw new RRException(message);
        }
    }

    public static void isPhone(String phone) {
        if (!Validator.isMobile(phone)) {
            throw new RRException("手机格式错误");
        }
    }

}
