package com.ozygod.model.zdgame.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class PrivateGameRecordEntity {
    private Long tableIndex;

    private Integer gameid;

    private Integer round;

    private Long userid;

    private Byte seat;

    private Integer score;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public Long getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(Long tableIndex) {
        this.tableIndex = tableIndex;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Byte getSeat() {
        return seat;
    }

    public void setSeat(Byte seat) {
        this.seat = seat;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}