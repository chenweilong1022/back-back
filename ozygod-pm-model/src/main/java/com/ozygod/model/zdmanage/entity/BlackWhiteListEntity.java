package com.ozygod.model.zdmanage.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("黑白名单实体")
public class BlackWhiteListEntity {
    @ApiModelProperty(value = "名单id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "当事人id", example = "1")
    private Integer partyId;

    @ApiModelProperty(value = "玩家id", example = "1")
    private Integer playerId;

    @ApiModelProperty(value = "玩家昵称", example = "1")
    private String playerNickname;

    @ApiModelProperty(value = "加入时间", notes = "yyyy-MM-dd HH:mm:ss", example = "2018-12-12 23:59:59")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @ApiModelProperty(value = "名单类型: 1:黑名单,2:白名单", example = "1")
    private Integer listType;

    @ApiModelProperty(value = "删除标志", example = "0")
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname == null ? null : playerNickname.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}