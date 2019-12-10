package com.ozygod.model.common.bo;

import com.ozygod.base.bo.BaseBO;
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
public class RobotConfigBO extends BaseBO {
    @ApiModelProperty("房间ID")
    private Integer roomId;
    @ApiModelProperty("机器人ID")
    private Integer uid;
    @ApiModelProperty("桌号")
    private Integer tableId;
    @ApiModelProperty("配置内容")
    private String config;
}
