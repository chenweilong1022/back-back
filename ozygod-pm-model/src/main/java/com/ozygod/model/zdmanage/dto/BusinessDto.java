package com.ozygod.model.zdmanage.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-03-12
 */
@Data
@ApiModel("玩家交易业务查询类")
public class BusinessDto extends BaseDto {
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("玩家ID")
    private Long playerId;
    @ApiModelProperty("显示id")
    private Long showId;
    @ApiModelProperty("提现类型")
    private Integer withdrawType;
    @ApiModelProperty("玩家账号")
    private String account;
    @ApiModelProperty("订单ID")
    private Long orderId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
