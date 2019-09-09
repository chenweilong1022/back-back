package com.ozygod.model.zdgame.entity;

import java.util.Date;

public class PoolEntity {
    private Long gameid;

    private String gamename;

    private Long roomid;

    private Long deskid;

    private Long money;

    private Date updatetime;

    public Long getGameid() {
        return gameid;
    }

    public void setGameid(Long gameid) {
        this.gameid = gameid;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename == null ? null : gamename.trim();
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Long getDeskid() {
        return deskid;
    }

    public void setDeskid(Long deskid) {
        this.deskid = deskid;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}