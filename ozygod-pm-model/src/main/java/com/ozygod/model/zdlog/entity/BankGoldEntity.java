package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel("银行金币实体类")
public class BankGoldEntity {
    private Long userid;

    private Integer reason;

    private Long beginGold;

    private Long changeGold;

    private Long taxGold;

    private Long endGold;

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

    public Long getBeginGold() {
        return beginGold;
    }

    public void setBeginGold(Long beginGold) {
        this.beginGold = beginGold;
    }

    public Long getChangeGold() {
        return changeGold;
    }

    public void setChangeGold(Long changeGold) {
        this.changeGold = changeGold;
    }

    public Long getTaxGold() {
        return taxGold;
    }

    public void setTaxGold(Long taxGold) {
        this.taxGold = taxGold;
    }

    public Long getEndGold() {
        return endGold;
    }

    public void setEndGold(Long endGold) {
        this.endGold = endGold;
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