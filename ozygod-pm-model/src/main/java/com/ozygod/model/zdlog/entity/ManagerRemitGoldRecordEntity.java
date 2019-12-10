package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ManagerRemitGoldRecordEntity {
    private Long id;

    private Long managerId;

    private Long userId;

    private String managerName;

    private String userAccount;

    private Long beginGold;

    private Long gold;

    private Long endGold;

    private String ip;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public Long getBeginGold() {
        return beginGold;
    }

    public void setBeginGold(Long beginGold) {
        this.beginGold = beginGold;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getEndGold() {
        return endGold;
    }

    public void setEndGold(Long endGold) {
        this.endGold = endGold;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}