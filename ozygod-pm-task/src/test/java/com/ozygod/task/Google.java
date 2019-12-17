package com.ozygod.task;

import cn.hutool.system.SystemUtil;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-16 10:34
 */
public class Google {

    public static void main(String[] args) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        String key1 = key.getKey();

        String otpAuthTotpURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("chenweilong", "chenweilong1022@gmail.com", key);
        System.out.println(otpAuthTotpURL);

        System.out.println(key1);


        // 用户登录时使用
        // 根据用户密钥和用户输入的密码，验证是否一致。（近3个密码都有效：前一个，当前，下一个）
        boolean isCodeValid = gAuth.authorize("YVLC6ILRRIOU4CIU", 496969);
        System.out.println(isCodeValid);

        // 根据密钥，获取最新密码（后台用不到，用来开发 谷歌身份验证器 客户端）
        int code = gAuth.getTotpPassword("NKITD62M26Z3AZGH");
        System.out.println(code);
    }
}
