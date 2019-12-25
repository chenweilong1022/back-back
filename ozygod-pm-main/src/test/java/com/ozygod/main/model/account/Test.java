package com.ozygod.main.model.account;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-23 14:06
 */
public class Test {

    public static void main(String[] args) {


        JSONObject connect_timed_out = JSONUtil.parseObj("connect timed out");
        System.out.println(connect_timed_out);


    }
}
