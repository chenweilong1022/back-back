package com.ozygod.model.zdspread.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordSpreadGiveGoldEntity {
    private Long recordId;

    private Integer spreadId;

    private Integer userId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    private Long oldGold;

    private Long giveGold;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Integer getSpreadId() {
        return spreadId;
    }

    public void setSpreadId(Integer spreadId) {
        this.spreadId = spreadId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Long getOldGold() {
        return oldGold;
    }

    public void setOldGold(Long oldGold) {
        this.oldGold = oldGold;
    }

    public Long getGiveGold() {
        return giveGold;
    }

    public void setGiveGold(Long giveGold) {
        this.giveGold = giveGold;
    }
}