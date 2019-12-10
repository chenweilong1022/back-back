package com.ozygod.model.zdlog.bo;

import com.ozygod.model.zdlog.entity.GameDiamondEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/10/29
 */
@Data
@ApiModel("玩家游戏钻石日志")
public class PlayerGameDiamondLogBO extends GameDiamondEntity {
    @ApiModelProperty(value = "玩家昵称")
    private String nickName;
}
