package com.ozygod.model.zdmanage.bo;

import com.ozygod.base.bo.BaseBO;
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
@ApiModel("代理总计")
public class AgentSummaryBO extends BaseBO {

    private Long firstPerformance;
    private Long secondPerformance;
    private Long thirdPerformance;
    private Long unlimitPerformance;
    private Long firstBonus;
    private Long secondBonus;
    private Long thirdBonus;
    private Long unlimitBonus;

    private Long totalBonus;

    private Long totalPerformance;

    public Long getTotalBonus() {
        return this.getFirstBonus() + this.getSecondBonus() + this.getThirdBonus() + this.getUnlimitBonus();
    }

    public Long getTotalPerformance() {
        return this.getFirstPerformance() + this.getSecondPerformance() + this.getThirdPerformance() + this.getUnlimitPerformance();
    }
}
