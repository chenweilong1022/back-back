package com.ozygod.base.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-16 10:48
 */
@Data
@Component
public class GoogleCode {

    @Value("${googleSecret}")
    private String googleSecret;

    public String getCode() {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        int code = gAuth.getTotpPassword(googleSecret);
        return String.valueOf(code);
    }

    public boolean checkCode(String code) {
        return code.equals(getCode());
    }

}
