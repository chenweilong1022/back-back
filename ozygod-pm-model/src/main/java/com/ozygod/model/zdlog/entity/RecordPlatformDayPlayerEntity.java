package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordPlatformDayPlayerEntity {
    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;

    private Integer dayReg;

    private Integer dayActive;

    private Integer dayLogin;

    private Integer dayMaxOnline;

    private Integer dayAvgOnline;

    private Integer dayNewRecharge;

    private Integer dayTotalRecharge;

    public Integer getDayNewRecharge() {
        return dayNewRecharge;
    }

    public void setDayNewRecharge(Integer dayNewRecharge) {
        this.dayNewRecharge = dayNewRecharge;
    }

    public Integer getDayTotalRecharge() {
        return dayTotalRecharge;
    }

    public void setDayTotalRecharge(Integer dayTotalRecharge) {
        this.dayTotalRecharge = dayTotalRecharge;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getDayReg() {
        return dayReg;
    }

    public void setDayReg(Integer dayReg) {
        this.dayReg = dayReg;
    }

    public Integer getDayActive() {
        return dayActive;
    }

    public void setDayActive(Integer dayActive) {
        this.dayActive = dayActive;
    }

    public Integer getDayLogin() {
        return dayLogin;
    }

    public void setDayLogin(Integer dayLogin) {
        this.dayLogin = dayLogin;
    }

    public Integer getDayMaxOnline() {
        return dayMaxOnline;
    }

    public void setDayMaxOnline(Integer dayMaxOnline) {
        this.dayMaxOnline = dayMaxOnline;
    }

    public Integer getDayAvgOnline() {
        return dayAvgOnline;
    }

    public void setDayAvgOnline(Integer dayAvgOnline) {
        this.dayAvgOnline = dayAvgOnline;
    }
}