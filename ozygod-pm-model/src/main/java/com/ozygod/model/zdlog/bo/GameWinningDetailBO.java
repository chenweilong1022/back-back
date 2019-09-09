package com.ozygod.model.zdlog.bo;

import com.ozygod.base.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-03-18
 */
@Data
@ApiModel("游戏输赢明细")
public class GameWinningDetailBO extends BaseBO {
    @ApiModelProperty("玩家id")
    private Long userId;
    @ApiModelProperty("显示ID")
    private Long showId;
    @ApiModelProperty("玩家昵称")
    private String nickname;
    @ApiModelProperty("房间ID")
    private Integer roomId;
    @ApiModelProperty("游戏名称")
    private String gameName;
    @ApiModelProperty("游戏人数")
    private Integer gamePersonCount;
    @ApiModelProperty("游戏输赢")
    private Long winningMoney;
    @ApiModelProperty("流水量")
    private Long flowVolume;
    @ApiModelProperty("税收总和")
    private Long totalTax;
}
