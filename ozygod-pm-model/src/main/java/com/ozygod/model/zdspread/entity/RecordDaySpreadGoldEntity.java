package com.ozygod.model.zdspread.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordDaySpreadGoldEntity {
    private Long reordId;

    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;

    private Integer spreadId;

    private Integer userId;

    private Long taxGold;

    private Long spreadGold;

    public Long getReordId() {
        return reordId;
    }

    public void setReordId(Long reordId) {
        this.reordId = reordId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
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

    public Long getTaxGold() {
        return taxGold;
    }

    public void setTaxGold(Long taxGold) {
        this.taxGold = taxGold;
    }

    public Long getSpreadGold() {
        return spreadGold;
    }

    public void setSpreadGold(Long spreadGold) {
        this.spreadGold = spreadGold;
    }
}