package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordPlatformSubsidyEntity {
    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;

    private Integer shareCount;

    private Integer signCount;

    private Integer subsidyCount;

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Integer getSubsidyCount() {
        return subsidyCount;
    }

    public void setSubsidyCount(Integer subsidyCount) {
        this.subsidyCount = subsidyCount;
    }
}