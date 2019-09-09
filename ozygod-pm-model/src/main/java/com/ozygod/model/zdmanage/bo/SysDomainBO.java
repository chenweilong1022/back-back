package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.SysDomainEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-20
 */
@Data
@ApiModel("域名管理业务类")
public class SysDomainBO extends SysDomainEntity {
    @ApiModelProperty("管理员id")
    private Integer managerId;
}
