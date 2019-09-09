package com.ozygod.model.zdspread.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordSpreadGiveDiamondEntity {
    private Long id;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    private Integer spreadId;

    private Integer userId;

    private Integer oldDiamond;

    private Integer giveDiamond;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getOldDiamond() {
        return oldDiamond;
    }

    public void setOldDiamond(Integer oldDiamond) {
        this.oldDiamond = oldDiamond;
    }

    public Integer getGiveDiamond() {
        return giveDiamond;
    }

    public void setGiveDiamond(Integer giveDiamond) {
        this.giveDiamond = giveDiamond;
    }
}