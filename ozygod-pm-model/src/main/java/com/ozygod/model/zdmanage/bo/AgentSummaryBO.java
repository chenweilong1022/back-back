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
    @ApiModelProperty("总业绩")
    private Long totalAllPerformance;
    @ApiModelProperty("总返佣")
    private Long totalRebate;
    @ApiModelProperty("总自营业绩")
    private Long totalPerformance;
    @ApiModelProperty("总直营业绩")
    private Long totalDirectPerformance;
    @ApiModelProperty("总团队业绩")
    private Long totalTeamPerformance;
    @ApiModelProperty("总代理人数")
    private Long totalAgentCount;
}
