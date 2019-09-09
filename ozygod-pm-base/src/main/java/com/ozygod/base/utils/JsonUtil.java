package com.ozygod.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.io.Serializable;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/5
 */
public class JsonUtil implements Serializable {
    private static final long serialVersionUID = 3720172427691576165L;

    /**
     * data如果是map的话，某一个key的value如果是空的话仍然参与json格式化，并且把null的值强制转成空字符串['']
     * @param data
     * @param writeNull
     * @return
     */
    public static String toJSONString(Object data, boolean writeNull) {
        if (writeNull) {
            return JSON.toJSONString(data, filter, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullStringAsEmpty);
        }
        return JSON.toJSONString(data);
    }

    private static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null)
                return "";
            return v;
        }
    };
    /**
     * 当进行toJSONString的时候，默认如果重用对象的话，会使用引用的方式进行引用对象。造成 "$ref":"" 问题
     * 传入参数SerializerFeature.DisableCircularReferenceDetect
     * @param data
     * @param features
     * @return
     */
    public static String toJSONString(Object data,SerializerFeature... features){
        return JSON.toJSONString(data, filter, features);
    }
}
