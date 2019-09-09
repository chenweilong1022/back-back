package com.ozygod.model.zdgame.bo;

import com.ozygod.model.zdgame.entity.OrderEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/20
 */
@Data
@ApiModel("玩家订单业务类")
public class PlayerOrderBO extends OrderEntity {
    private String goodsName;
    private Long goodsVal;
    private String goodsType;
    private String nickName;
    @ApiModelProperty("显示id")
    private Long showId;
    @ApiModelProperty("订单结果")
    private String result;
}
