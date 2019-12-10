package com.ozygod.model.zdgame.dto;

import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/28
 */
@Data
public class RemitDto extends BaseDto {
    @ApiModelProperty("玩家id")
    private Integer playerId;
    @ApiModelProperty("玩家显示id")
    private Long showId;
    private Integer managerId;
    private Long remitGold;
    private Long remitDiamond;
}
