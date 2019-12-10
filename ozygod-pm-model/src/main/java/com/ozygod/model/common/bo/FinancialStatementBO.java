package com.ozygod.model.common.bo;

import com.ozygod.base.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-03-25
 */
@Data
@ApiModel("财务对账单业务类")
public class FinancialStatementBO extends BaseBO {
    @ApiModelProperty("类型名称")
    private String typeName;
    @ApiModelProperty("金额")
    private Long amount;
    @ApiModelProperty("税额")
    private Long tax;
    @ApiModelProperty("实际到账")
    private Long actualAmount;
}
