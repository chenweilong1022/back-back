package com.ozygod.model.common.dto;

import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-05
 */
@Data
@ApiModel("机器人查询DTO")
public class RobotDto extends BaseDto {
    @ApiModelProperty("房间ID")
    private Integer roomId;
}
