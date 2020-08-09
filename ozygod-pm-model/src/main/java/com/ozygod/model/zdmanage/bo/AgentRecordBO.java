package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.AgentRecordEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-08
 */
@Data
@ApiModel("代理结算记录")
public class AgentRecordBO extends AgentRecordEntity {
    @ApiModelProperty("用户帐号")
    private String userAccount;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("结算次数")
    private Integer billingTimes;
    @ApiModelProperty("显示id")
    private Long showId;

    private Long totalBonus;

    private Long totalPerformance;

    private List<AgentRecordBO> childrens;

    private Integer childrenCount = 0;

    public Long getTotalBonus() {
        return this.getFirstBonus() + this.getSecondBonus() + this.getThirdBonus() + this.getUnlimitBonus();
    }

    public Long getTotalPerformance() {
        return this.getFirstPerformance() + this.getSecondPerformance() + this.getThirdPerformance() + this.getUnlimitPerformance();
    }
}
