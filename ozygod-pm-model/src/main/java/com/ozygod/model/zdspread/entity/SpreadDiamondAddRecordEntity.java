package com.ozygod.model.zdspread.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SpreadDiamondAddRecordEntity {
    private Long id;

    private Integer spreadId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    private Integer addDiamond;

    private String orderNum;

    private Integer orderDiamond;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpreadId() {
        return spreadId;
    }

    public void setSpreadId(Integer spreadId) {
        this.spreadId = spreadId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getAddDiamond() {
        return addDiamond;
    }

    public void setAddDiamond(Integer addDiamond) {
        this.addDiamond = addDiamond;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getOrderDiamond() {
        return orderDiamond;
    }

    public void setOrderDiamond(Integer orderDiamond) {
        this.orderDiamond = orderDiamond;
    }
}
