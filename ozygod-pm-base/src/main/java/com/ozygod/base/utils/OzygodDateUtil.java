package com.ozygod.base.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

/**
 * 时间工具类
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-03 11:19
 */
public class OzygodDateUtil {


    public static DateTime beginOfDay(DateTime dateTime) {
        if (ObjectUtil.isNull(dateTime)) {
            return dateTime;
        }
        DateTime beginOfDay = DateUtil.beginOfDay(dateTime);
        /**
         * 今日第一秒
         */
        return beginOfDay;
    }


    public static DateTime endOfDay(DateTime dateTime) {
        if (ObjectUtil.isNull(dateTime)) {
            return dateTime;
        }
        DateTime date = DateUtil.endOfDay(dateTime);
        /**
         * 今日最后一分钟
         */
        DateTime endOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(date));
        return endOfDay;
    }

}
