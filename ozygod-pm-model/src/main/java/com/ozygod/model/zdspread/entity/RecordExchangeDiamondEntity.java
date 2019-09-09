package com.ozygod.model.zdspread.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordExchangeDiamondEntity {
    private Long recordId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    private Integer spreadId;

    private Integer oldDiamond;

    private Integer exchangeDiamond;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public Integer getOldDiamond() {
        return oldDiamond;
    }

    public void setOldDiamond(Integer oldDiamond) {
        this.oldDiamond = oldDiamond;
    }

    public Integer getExchangeDiamond() {
        return exchangeDiamond;
    }

    public void setExchangeDiamond(Integer exchangeDiamond) {
        this.exchangeDiamond = exchangeDiamond;
    }
}