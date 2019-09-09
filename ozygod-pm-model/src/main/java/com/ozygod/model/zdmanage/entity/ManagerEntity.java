package com.ozygod.model.zdmanage.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("管理员帐号实体类")
public class ManagerEntity {
    @ApiModelProperty(value = "管理员id", example = "1")
    private Integer manageid;

    @ApiModelProperty(value = "登录名", example = "admin")
    private String loginName;

    @ApiModelProperty(value = "登录密码", example = "123456")
    private String loginPwd;

    @ApiModelProperty(value = "角色id", example = "1")
    private Integer roleid;

    @ApiModelProperty(value = "昵称", example = "管理员")
    private String nickName;

    @ApiModelProperty(value = "最后登录ip", example = "0.0.0.0")
    private String lastLoginIp;

    @ApiModelProperty(value = "最后登录时间", example = "1900-01-01 01:01:01")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    @ApiModelProperty(value = "删除标志", example = "0")
    private Boolean isdel;

    public Integer getManageid() {
        return manageid;
    }

    public void setManageid(Integer manageid) {
        this.manageid = manageid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }
}