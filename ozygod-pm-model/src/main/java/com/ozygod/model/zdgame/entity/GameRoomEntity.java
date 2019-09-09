package com.ozygod.model.zdgame.entity;

public class GameRoomEntity {
    private Integer roomid;

    private Integer gameid;

    private String roomName;

    private Boolean isClub;

    private String roomPwd;

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public Boolean getIsClub() {
        return isClub;
    }

    public void setIsClub(Boolean isClub) {
        this.isClub = isClub;
    }

    public String getRoomPwd() {
        return roomPwd;
    }

    public void setRoomPwd(String roomPwd) {
        this.roomPwd = roomPwd == null ? null : roomPwd.trim();
    }
}