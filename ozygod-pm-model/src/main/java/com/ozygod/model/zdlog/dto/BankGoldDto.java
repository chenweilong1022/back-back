package com.ozygod.model.zdlog.dto;

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
 * @date: 2018/9/19
 */
@Data
@ApiModel("银行金币查询DTO")
public class BankGoldDto extends BaseDto {
    @ApiModelProperty(value = "玩家id", example = "1")
    private Integer playerId;
    @ApiModelProperty(value = "起始时间",example = "2017-06-06 12:12:12")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty(value = "结束时间",example = "2017-07-06 12:12:12")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty("原因")
    private Integer reason;
}
