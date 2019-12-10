package com.ozygod.model.zdgame.bo;

import com.ozygod.model.zdgame.entity.GameEntity;
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
@ApiModel("游戏业务类")
public class GameBO extends GameEntity {
    @ApiModelProperty("在线玩家数量")
    private Integer onlineCount;
}
