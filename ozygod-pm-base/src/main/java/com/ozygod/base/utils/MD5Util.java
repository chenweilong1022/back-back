package com.ozygod.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @title: MD5算法
 * @description:
 * @author: Joey
 * @date: Created in 18:22 2018/8/28 0028.
 */
public class MD5Util {
    /**
     *  全局数组
     */
    private final static String[] DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 返回形式为数字跟字符串
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return DIGITS[iD1] + DIGITS[iD2];
    }

    /**
     * 转换字节数组为16进制字串
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String encode(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    public static String encode16(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(strObj.getBytes())).substring(8, 24);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

//    public static void main(String[] args) {
//        System.out.println(MD5Util.encode("123456789qaz"+"keyCxzU!.,"));
//    }
}
