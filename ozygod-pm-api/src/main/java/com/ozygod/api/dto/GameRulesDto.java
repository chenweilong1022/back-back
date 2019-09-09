package com.ozygod.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/15
 */
@Data
@ApiModel("游戏规则查询条件")
public class GameRulesDto implements Serializable {
    private static final long serialVersionUID = 4607844518141407424L;
    @ApiModelProperty(value = "游戏id", example = "1")
    private Integer gameId;
    @ApiModelProperty(value = "房间号", example = "1")
    private Integer roomId;
    @ApiModelProperty(value = "桌号", example = "1")
    private Integer tableId;
}
