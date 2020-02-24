package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class GameGoldEntity {
    private Long userid;

    private String reason;

    private Integer roomid;

    private Integer tableid;

    private Integer seatid;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getTableid() {
        return tableid;
    }

    public void setTableid(Integer tableid) {
        this.tableid = tableid;
    }

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
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
