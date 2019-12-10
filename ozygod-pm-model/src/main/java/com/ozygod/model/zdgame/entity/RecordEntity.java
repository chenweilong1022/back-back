package com.ozygod.model.zdgame.entity;

import java.util.Date;

public class RecordEntity extends RecordEntityKey {
    private Integer playCnt;

    private Integer winCnt;

    private Integer loseCnt;

    private Integer fleeCnt;

    private Date createTime;

    private Date lastTime;

    private Long totalGold;

    public Integer getPlayCnt() {
        return playCnt;
    }

    public void setPlayCnt(Integer playCnt) {
        this.playCnt = playCnt;
    }

    public Integer getWinCnt() {
        return winCnt;
    }

    public void setWinCnt(Integer winCnt) {
        this.winCnt = winCnt;
    }

    public Integer getLoseCnt() {
        return loseCnt;
    }

    public void setLoseCnt(Integer loseCnt) {
        this.loseCnt = loseCnt;
    }

    public Integer getFleeCnt() {
        return fleeCnt;
    }

    public void setFleeCnt(Integer fleeCnt) {
        this.fleeCnt = fleeCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Long getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(Long totalGold) {
        this.totalGold = totalGold;
    }
}