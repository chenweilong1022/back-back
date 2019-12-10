package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RecordPlatformDayTaxGoldEntity {
    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;

    private Integer gameId;

    private String channelId;

    private Long dayGameTax;

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Long getDayGameTax() {
        return dayGameTax;
    }

    public void setDayGameTax(Long dayGameTax) {
        this.dayGameTax = dayGameTax;
    }
}