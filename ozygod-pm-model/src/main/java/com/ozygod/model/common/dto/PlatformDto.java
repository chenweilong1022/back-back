package com.ozygod.model.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/20
 */
@Data
@ApiModel("平台数据查询条件DTO")
public class PlatformDto extends BaseDto {
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    private Integer gameId;
    @ApiModelProperty(value = "渠道")
    private String channel;
    @ApiModelProperty(value = "公告状态")
    private Byte noticeState;
    @ApiModelProperty(value = "公告类型")
    private Byte noticeType;
    @ApiModelProperty("状态")
    private Integer state;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty("玩家id")
    private Integer playerId;
    @ApiModelProperty("玩家帐号")
    private String account;
    @ApiModelProperty("管理员id")
    private Integer managerId;
    @ApiModelProperty("上级id")
    private Long superId;
    @ApiModelProperty("期次")
    private Integer period;
    @ApiModelProperty("显示id")
    private Long showId;
    @ApiModelProperty("查询类型")
    private String queryType;
}
