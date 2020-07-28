package com.ozygod.main.model.account;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.StringJoiner;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-23 14:06
 */
public class Test {

//    public static void main(String[] args) {
//
//        create_tbl_account();
//
//
//    }


    /**
     * 生成新用户
     */
    public static void create_tbl_account() {

        String pre = "insert into tbl_account (account,pwd,channel,app_channel,login_time,logout_time,create_time,token,token_invalid_time,platform,vip_type,vip_invalid_time) values ";

        StringJoiner stringJoinerall = new StringJoiner("",pre,"");

        for (int i = 0; i < 100; i++) {
            StringJoiner stringJoiner = new StringJoiner("","(",")");
            String s = RandomUtil.randomString(10);
            stringJoiner.add("'" + s + "'" + ",");
            stringJoiner.add("'" + "123" + "'" + ",");
            stringJoiner.add("'" + "guest" + "'" + ",");
            stringJoiner.add("'" + "3521" + "'" + ",");
            stringJoiner.add("'" + DateUtil.formatDateTime(DateUtil.date()) + "'" + ",");
            stringJoiner.add("'" + DateUtil.formatDateTime(DateUtil.date()) + "'" + ",");
            stringJoiner.add("'" + DateUtil.formatDateTime(DateUtil.date()) + "'" + ",");
            stringJoiner.add("'" + RandomUtil.randomString(20) + "'" + ",");
            stringJoiner.add("'" + DateUtil.formatDateTime(DateUtil.date()) + "'" + ",");
            stringJoiner.add("'" + "IOS" + "'" + ",");
            stringJoiner.add("'" + "1" + "'" + ",");
            stringJoiner.add("'" + DateUtil.formatDateTime(DateUtil.date()) + "'");


            stringJoinerall.add(stringJoiner.toString());
            stringJoinerall.add(",");
        }


        System.out.println(stringJoinerall);

    }


}
