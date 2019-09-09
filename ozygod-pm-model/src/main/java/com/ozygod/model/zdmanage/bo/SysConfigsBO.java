package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.SysConfigsEntity;
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
@ApiModel("系统配置项管理")
public class SysConfigsBO extends SysConfigsEntity {
    @ApiModelProperty("管理员ID")
    private Integer managerId;
}
