package com.ozygod.model.zdgame.bo;

import com.ozygod.base.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/19
 */
@Data
@ApiModel("平台概况业务对象")
public class PlatformOverviewBO extends BaseBO {
    @ApiModelProperty(value = "平台总用户数")
    private Integer totalUser;
    @ApiModelProperty(value = "平台冻结用户数")
    private Integer lockUser;
    @ApiModelProperty(value = "今日活跃用户")
    private Integer todayLogin;
    @ApiModelProperty(value = "平台金币总和")
    private Long totalGold;
    @ApiModelProperty(value = "在线玩家平台金币总和")
    private Long onlineTotalGold;
    @ApiModelProperty(value = "平台钻石总和")
    private Long totalDiamond;
    @ApiModelProperty(value = "平台充值总额")
    private Long totalOrderPay;
    @ApiModelProperty(value = "平台税收总额")
    private Long totalTax;
    @ApiModelProperty(value = "今日注册用户数")
    private Integer todayRegNum;
    @ApiModelProperty(value = "今日最高在线用户数")
    private Integer todayMaxOnline;
    @ApiModelProperty(value = "当前在线用户数")
    private Integer nowOnline;
    @ApiModelProperty(value = "平台冻结用户金币总数")
    private Long lockUserGold;
}
