package com.ozygod.base.annotations;

import com.ozygod.base.enums.MoneyFormatType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-20 15:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface MoneyField {

    MoneyFormatType[] moneyFormatTypes();

    String format() default "";

    Class<?> fieldType() default Long.class;
}
