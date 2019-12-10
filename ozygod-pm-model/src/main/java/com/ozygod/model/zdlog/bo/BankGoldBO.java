package com.ozygod.model.zdlog.bo;

import com.ozygod.model.zdlog.entity.BankGoldEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/19
 */
@Data
@ApiModel("银行金币业务对象")
public class BankGoldBO extends BankGoldEntity {
    @ApiModelProperty(value = "玩家昵称", example = "玩家")
    private String nickName;
}
