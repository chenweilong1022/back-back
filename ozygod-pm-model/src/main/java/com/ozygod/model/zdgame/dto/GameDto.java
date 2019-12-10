package com.ozygod.model.zdgame.dto;

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
@ApiModel("游戏查询业务类")
public class GameDto extends BaseDto {
    @ApiModelProperty("游戏ID")
    private Integer gameId;
}
