package com.ozygod.model.zdmanage.dto;

import com.ozygod.base.dto.BaseDto;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/30
 */
@Data
public class ManagerDto extends BaseDto {
    private Integer managerId;
    private Integer whoId;
    private Integer roleId;
    private Integer isDel;
    private Integer logTypeId;
    private Integer queryType;
}
