package com.ozygod.model.zdgame.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.annotations.MoneyField;
import com.ozygod.base.enums.MoneyFormatType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-08-09 15:54
 */
@Data
public class AgentTreeVo {


    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "显示id")
    private Long showId;
    @ApiModelProperty(value = "用户id")
    private Long userid;
    @ApiModelProperty(value = "下级")
    private List<AgentTreeVo> childrens;

    @ApiModelProperty(value = "label")
    private String label;

    @ApiModelProperty(value = "充值金额")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private Integer rechargePrice;

    @ApiModelProperty(value = "直属金额")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private Integer underRechargePrice;

    @ApiModelProperty(value = "团队金额")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private Integer teamRechargePrice;

    @ApiModelProperty(value = "提现金额")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private Integer withdrawAmount;

    @ApiModelProperty(value = "直属金额")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private Integer underWithdrawAmount;

    @ApiModelProperty(value = "团队金额")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private Integer teamWithdrawAmount;


    private Integer count;

    private Integer teamCount;

    public Integer getCount() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.childrens.size();
    }


    public Integer getWithdrawAmount() {
        return withdrawAmount;
    }

    public Integer getUnderWithdrawAmount() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.childrens.stream().mapToInt(agentTreeVo -> ObjectUtil.isNull(agentTreeVo.getWithdrawAmount()) ? 0 : agentTreeVo.getWithdrawAmount()).sum();
    }

    public Integer getTeamWithdrawAmount() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.getWithdrawAmount() + this.childrens.stream().mapToInt(agentTreeVo -> agentTreeVo.getTeamWithdrawAmount()).sum();
    }

    public Integer getRechargePrice() {
        return rechargePrice;
    }

    public Integer getUnderRechargePrice() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.childrens.stream().mapToInt(agentTreeVo -> ObjectUtil.isNull(agentTreeVo.getRechargePrice()) ? 0 : agentTreeVo.getRechargePrice()).sum();
    }

    public Integer getTeamRechargePrice() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.getRechargePrice() + this.childrens.stream().mapToInt(agentTreeVo -> agentTreeVo.getTeamRechargePrice()).sum();
    }

    public Integer getTeamCount() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.getCount() + this.childrens.stream().mapToInt(agentTreeVo -> agentTreeVo.getTeamCount()).sum();
    }

    public String getLabel() {
        return String.format("%s-(%s)",this.nickname,this.showId);
    }
}
