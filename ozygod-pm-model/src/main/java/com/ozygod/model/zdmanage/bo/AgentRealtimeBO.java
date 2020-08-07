package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.AgentRealtimeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-08
 */
@Data
@ApiModel("当期代理数据业务类")
public class AgentRealtimeBO extends AgentRealtimeEntity {
    @ApiModelProperty("用户帐号")
    private String userAccount;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("显示id")
    private Long showId;

    private String type;


    private Long totalBonus;

    private Long totalPerformance;

    public Long getTotalBonus() {
        return this.getFirstBonus() + this.getSecondBonus() + this.getThirdBonus() + this.getUnlimitBonus();
    }

    public Long getTotalPerformance() {
        return this.getFirstPerformance() + this.getSecondPerformance() + this.getThirdPerformance() + this.getUnlimitPerformance();
    }
}
