package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class GameDiamondEntity {
    private Long userid;

    private Integer reason;

    private Long beginVal;

    private Long changeVal;

    private Long tax;

    private Long endVal;

    private String note;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Long getBeginVal() {
        return beginVal;
    }

    public void setBeginVal(Long beginVal) {
        this.beginVal = beginVal;
    }

    public Long getChangeVal() {
        return changeVal;
    }

    public void setChangeVal(Long changeVal) {
        this.changeVal = changeVal;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Long getEndVal() {
        return endVal;
    }

    public void setEndVal(Long endVal) {
        this.endVal = endVal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}