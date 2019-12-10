package com.ozygod.model.zdlog.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/21
 */
@Data
@ApiModel("按日统计玩家数业务类")
public class DailyPlayersCountBO extends BaseBO {
    @ApiModelProperty(value = "统计日期")
    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;
    @ApiModelProperty(value = "日注册数")
    private Integer dayReg;
    @ApiModelProperty(value = "日活跃数")
    private Integer dayActive;
    @ApiModelProperty(value = "日登录次数")
    private Integer dayLogin;
    @ApiModelProperty(value = "日最高在线")
    private Integer dayMaxOnline;
    @ApiModelProperty(value = "日平均在线")
    private Integer dayAvgOnline;
    @ApiModelProperty(value = "日新增充值玩家")
    private Integer dayNewRecharge;
    @ApiModelProperty(value = "日总充值玩家")
    private Integer dayTotalRecharge;
}
