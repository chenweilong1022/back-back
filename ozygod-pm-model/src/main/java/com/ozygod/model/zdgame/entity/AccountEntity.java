package com.ozygod.model.zdgame.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class AccountEntity {
    private Long userid;

    private String account;

    private String pwd;

    private String channel;

    private String appChannel;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lock;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date logoutTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String token;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date tokenInvalidTime;

    private String platform;

    private String ip;

    private Byte vipType;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date vipInvalidTime;

    private String phoneNum;

    private Long saler;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date bindTime;

    private String realName;

    private String idCode;

    private String guest;

    private Long introducer;

    private String unid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel == null ? null : appChannel.trim();
    }

    public Date getLock() {
        return lock;
    }

    public void setLock(Date lock) {
        this.lock = lock;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getTokenInvalidTime() {
        return tokenInvalidTime;
    }

    public void setTokenInvalidTime(Date tokenInvalidTime) {
        this.tokenInvalidTime = tokenInvalidTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Byte getVipType() {
        return vipType;
    }

    public void setVipType(Byte vipType) {
        this.vipType = vipType;
    }

    public Date getVipInvalidTime() {
        return vipInvalidTime;
    }

    public void setVipInvalidTime(Date vipInvalidTime) {
        this.vipInvalidTime = vipInvalidTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Long getSaler() {
        return saler;
    }

    public void setSaler(Long saler) {
        this.saler = saler;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode == null ? null : idCode.trim();
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest == null ? null : guest.trim();
    }

    public Long getIntroducer() {
        return introducer;
    }

    public void setIntroducer(Long introducer) {
        this.introducer = introducer;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid == null ? null : unid.trim();
    }
}