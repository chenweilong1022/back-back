package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ManagerRemitDiamondRecordEntity {
    private Long id;

    private Integer managerId;

    private Integer userId;

    private Long beginDiamond;

    private Long changeDiamond;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getBeginDiamond() {
        return beginDiamond;
    }

    public void setBeginDiamond(Long beginDiamond) {
        this.beginDiamond = beginDiamond;
    }

    public Long getChangeDiamond() {
        return changeDiamond;
    }

    public void setChangeDiamond(Long changeDiamond) {
        this.changeDiamond = changeDiamond;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}