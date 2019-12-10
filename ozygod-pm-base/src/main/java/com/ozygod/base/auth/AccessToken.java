package com.ozygod.base.auth;

import java.io.Serializable;

/**
 * @title: 用户token
 * @description:
 * @author: Joey
 * @date: Created on 2018/8/30 0030
 */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = 7849169699481679609L;
    /**访问token*/
    private String tokenid;
    /**用户ID*/
    private String userid;
    /**用户ID*/
    private String username;
    /** 所在公司ID */
    private String companyid;
    /**所在公司名称*/
    private String companyname;
    /** 所在公司类型 */
    private String companytype;
    /**用户部门ID*/
    private String deptid;
    /**登录名*/
    private String loginname;
    /**有效期:秒,-1表示永久有效*/
    private Long expiresecs;
    /**创建时间，绝对秒数*/
    private Long createtime;

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getExpiresecs() {
        return expiresecs;
    }

    public void setExpiresecs(Long expiresecs) {
        this.expiresecs = expiresecs;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
