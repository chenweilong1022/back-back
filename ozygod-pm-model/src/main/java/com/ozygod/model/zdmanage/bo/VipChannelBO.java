package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.VipChannelEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019/1/15
 */
@Data
@ApiModel("Vip充值通道")
public class VipChannelBO extends VipChannelEntity {
    @ApiModelProperty("管理员ID")
    private Integer managerId;
}
