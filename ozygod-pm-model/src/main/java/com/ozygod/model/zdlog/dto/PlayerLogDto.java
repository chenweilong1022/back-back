package com.ozygod.model.zdlog.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/21
 */
@Data
public class PlayerLogDto extends BaseDto {
    private Long playerId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Long managerId;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    @ApiModelProperty("游戏id列表")
    private List<Integer> gameIds;
    @ApiModelProperty("原因")
    private Integer reason;
    @ApiModelProperty("管理员账号")
    private String managerAccount;
    @ApiModelProperty("显示id")
    private Long showId;
    @ApiModelProperty("房间id")
    private Integer roomId;
    @ApiModelProperty("在线用户id")
    private List<Long> onlineUserIds;
    @ApiModelProperty("是否在线")
    private boolean online = false;
}
