package com.ozygod.model.zdgame.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/20
 */
@Data
@ApiModel("玩家订单查询DTO")
public class PlayerOrderDto extends BaseDto {
    @ApiModelProperty(value = "玩家昵称")
    private String nickName;
    @ApiModelProperty(value = "玩家ID")
    private Integer playerId;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty("显示id")
    private Long showId;
    @ApiModelProperty("订单状态")
    private Integer state;
    @ApiModelProperty("在线玩家id")
    private List<Long> onlinePlayerIds;
}
