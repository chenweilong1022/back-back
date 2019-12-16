package com.ozygod.model.zdmanage.dto;

import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/5
 */
@Data
public class LoginDto {
    private String username;
    private String password;
    private String googleCode;
    private String loginFrom;
    private String userIP;
    private String userAgent;
}
