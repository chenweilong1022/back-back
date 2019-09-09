package com.ozygod.model.zdgame.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/20
 */
@Data
@ApiModel("充值总计业务对象")
public class RechargeCountBO {
    @ApiModelProperty(value = "充值总计")
    private Long totalPay;
    @ApiModelProperty(value = "万通支付充值总计")
    private Long totalWanTongPay;
    @ApiModelProperty(value = "风云支付充值总计")
    private Long totalFengYunPay;
    @ApiModelProperty(value = "微信支付充值总计")
    private Long totalWeixin;
}
