package com.ozygod.base.enums;

/**
 * 缓存名称枚举
 *
 */
public enum CacheName {
    LOGIN_TOKEN_ACCOUNT("LOGIN_TOKEN_ACCOUNT", "登录token对应的账号"),
    ACCOUNT_LOGIN_TOKEN("ACCOUNT_LOGIN_TOKEN", "账号对应的登录token"),
    USERID_LOGIN_TOKEN("USERID_LOGIN_TOKEN", "USERID对应的登录token");

    private String code;

    private String desc;

    private CacheName(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
