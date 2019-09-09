package com.ozygod.model.zdgame.entity;

import java.util.Date;

public class PlayerInfoEntity {
    private Long userid;

    private String nickname;

    private String secondPwd;

    private Byte sex;

    private Long gold;

    private Long bankGold;

    private Long bindGold;

    private Long honor;

    private Long diamond;

    private Integer sendGift;

    private Date createTime;

    private Date lastUpdate;

    private String imageid;

    private Long showId;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSecondPwd() {
        return secondPwd;
    }

    public void setSecondPwd(String secondPwd) {
        this.secondPwd = secondPwd == null ? null : secondPwd.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getBankGold() {
        return bankGold;
    }

    public void setBankGold(Long bankGold) {
        this.bankGold = bankGold;
    }

    public Long getBindGold() {
        return bindGold;
    }

    public void setBindGold(Long bindGold) {
        this.bindGold = bindGold;
    }

    public Long getHonor() {
        return honor;
    }

    public void setHonor(Long honor) {
        this.honor = honor;
    }

    public Long getDiamond() {
        return diamond;
    }

    public void setDiamond(Long diamond) {
        this.diamond = diamond;
    }

    public Integer getSendGift() {
        return sendGift;
    }

    public void setSendGift(Integer sendGift) {
        this.sendGift = sendGift;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid == null ? null : imageid.trim();
    }
}