package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.WithdrawOrderEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-03-12
 */
@Data
@ApiModel("玩家提现业务类")
public class WithdrawOrderBO extends WithdrawOrderEntity {
    @ApiModelProperty("玩家昵称")
    private String nickname;
    @ApiModelProperty("审核人账号")
    private String approvalAccount;
    @ApiModelProperty("显示id")
    private Long showId;
}
