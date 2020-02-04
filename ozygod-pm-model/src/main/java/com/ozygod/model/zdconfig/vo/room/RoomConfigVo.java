package com.ozygod.model.zdconfig.vo.room;

import com.ozygod.base.bo.BaseBO;
import com.ozygod.model.zdconfig.vo.game.BaseGameConfigVo;
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
@ApiModel("机器人配置业务类")
public class RoomConfigVo extends BaseBO {

    @ApiModelProperty("房间ID")
    private Integer roomId;

    private Integer gameId;

    private String gameName;

    private String cardName;

    private BaseGameConfigVo baseGameConfigVo;
}
