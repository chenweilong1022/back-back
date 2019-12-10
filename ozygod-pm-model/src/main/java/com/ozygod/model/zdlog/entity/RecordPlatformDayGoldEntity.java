package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordPlatformDayGoldEntity {
    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;

    private Long dayTransferTax;

    private Long dayGameTax;

    private Long dayPayGold;

    private Long dayFreeGold;

    private Long dayGrowHonor;

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Long getDayTransferTax() {
        return dayTransferTax;
    }

    public void setDayTransferTax(Long dayTransferTax) {
        this.dayTransferTax = dayTransferTax;
    }

    public Long getDayGameTax() {
        return dayGameTax;
    }

    public void setDayGameTax(Long dayGameTax) {
        this.dayGameTax = dayGameTax;
    }

    public Long getDayPayGold() {
        return dayPayGold;
    }

    public void setDayPayGold(Long dayPayGold) {
        this.dayPayGold = dayPayGold;
    }

    public Long getDayFreeGold() {
        return dayFreeGold;
    }

    public void setDayFreeGold(Long dayFreeGold) {
        this.dayFreeGold = dayFreeGold;
    }

    public Long getDayGrowHonor() {
        return dayGrowHonor;
    }

    public void setDayGrowHonor(Long dayGrowHonor) {
        this.dayGrowHonor = dayGrowHonor;
    }
}