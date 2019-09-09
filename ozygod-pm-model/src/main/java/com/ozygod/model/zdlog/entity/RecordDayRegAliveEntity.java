package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordDayRegAliveEntity {
    private Long id;

    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;

    private Integer regNum;

    private Integer morrowAliveNum;

    private Integer morrowAliveRatio;

    private Integer thirdAliveNum;

    private Integer thirdAliveRatio;

    private Integer seventhAliveNum;

    private Integer seventhAliveRatio;

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

    public Integer getRegNum() {
        return regNum;
    }

    public void setRegNum(Integer regNum) {
        this.regNum = regNum;
    }

    public Integer getMorrowAliveNum() {
        return morrowAliveNum;
    }

    public void setMorrowAliveNum(Integer morrowAliveNum) {
        this.morrowAliveNum = morrowAliveNum;
    }

    public Integer getMorrowAliveRatio() {
        return morrowAliveRatio;
    }

    public void setMorrowAliveRatio(Integer morrowAliveRatio) {
        this.morrowAliveRatio = morrowAliveRatio;
    }

    public Integer getThirdAliveNum() {
        return thirdAliveNum;
    }

    public void setThirdAliveNum(Integer thirdAliveNum) {
        this.thirdAliveNum = thirdAliveNum;
    }

    public Integer getThirdAliveRatio() {
        return thirdAliveRatio;
    }

    public void setThirdAliveRatio(Integer thirdAliveRatio) {
        this.thirdAliveRatio = thirdAliveRatio;
    }

    public Integer getSeventhAliveNum() {
        return seventhAliveNum;
    }

    public void setSeventhAliveNum(Integer seventhAliveNum) {
        this.seventhAliveNum = seventhAliveNum;
    }

    public Integer getSeventhAliveRatio() {
        return seventhAliveRatio;
    }

    public void setSeventhAliveRatio(Integer seventhAliveRatio) {
        this.seventhAliveRatio = seventhAliveRatio;
    }
}