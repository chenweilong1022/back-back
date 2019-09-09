package com.ozygod.model.zdgame.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/18
 */
@Data
@ApiModel("玩家帐号查询条件dto")
public class PlayerAccountDto extends BaseDto {
    @ApiModelProperty(value = "玩家帐号",example = "123")
    private String account;
    @ApiModelProperty(value = "玩家昵称", example = "玩家")
    private String nickName;
    @ApiModelProperty(value = "玩家id", example = "1")
    private Integer playerId;
    @ApiModelProperty(value = "玩家id", example = "1")
    private Long showId;
    @ApiModelProperty(value = "推广者id", example = "1")
    private Long salerId;
    @ApiModelProperty(value = "最后登录IP地址", example = "192.168.1.1")
    private String lastLoginIp;
    @ApiModelProperty(value = "用户状态 0全部, 1正常，2冻结", example = "0")
    private Integer state;
    @ApiModelProperty(value = "注册起始时间",example = "2017-06-06 12:12:12")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registBeginTime;
    @ApiModelProperty(value = "注册结束时间",example = "2017-07-06 12:12:12")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registEndTime;
    @ApiModelProperty(value = "登录起始时间",example = "2017-06-06 12:12:12")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date loginBeginTime;
    @ApiModelProperty(value = "登录结束时间",example = "2017-07-06 12:12:12")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date loginEndTime;
    @ApiModelProperty(value = "起始日期",example = "2017-06-06")
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @ApiModelProperty(value = "结束日期",example = "2017-07-06")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    @ApiModelProperty("起始时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty("玩家类型")
    private List<Integer> playerTypes;
    @ApiModelProperty("游戏id列表")
    private List<Integer> gameIds;
    @ApiModelProperty("游戏ID")
    private Integer gameId;
}
