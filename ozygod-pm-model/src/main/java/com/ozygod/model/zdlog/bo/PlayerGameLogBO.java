package com.ozygod.model.zdlog.bo;

import com.ozygod.model.zdlog.entity.GameGoldEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/21
 */
@Data
@ApiModel("玩家游戏日志业务类")
public class PlayerGameLogBO extends GameGoldEntity {
    @ApiModelProperty(value = "玩家昵称")
    private String nickName;
    @ApiModelProperty(value = "游戏id")
    private String gameId;
    @ApiModelProperty(value = "游戏名称")
    private String gameName;
    @ApiModelProperty("显示id")
    private Long showId;
}
