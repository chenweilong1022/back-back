package com.ozygod.model.zdgame.bo;

import com.ozygod.model.zdgame.entity.GameRoomEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-03-15
 */
@Data
@ApiModel("游戏房间业务类")
public class GameRoomBO extends GameRoomEntity {
    @ApiModelProperty("游戏名称")
    private String gameName;
}
