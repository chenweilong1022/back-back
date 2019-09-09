package com.ozygod.model.zdgame.bo;

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
 * @date: 2018/9/20
 */
@Data
@ApiModel("按日统计充值金额业务对象")
public class DailyRechargeCountBO extends BaseBO {
    @ApiModelProperty(value = "日期时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date orderDay;
    @ApiModelProperty(value = "当日充值总额")
    private Long totalPay;
    @ApiModelProperty(value = "万通支付")
    private Long wanTongPay;
    @ApiModelProperty(value = "风云支付")
    private Long fengYunPay;
    @ApiModelProperty(value = "微信支付")
    private Long weixin;
}
