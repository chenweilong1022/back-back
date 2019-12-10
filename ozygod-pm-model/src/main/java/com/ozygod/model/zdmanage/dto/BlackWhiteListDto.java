package com.ozygod.model.zdmanage.dto;

import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/14
 */
@Data
@ApiModel("黑白名单查询DTO")
public class BlackWhiteListDto extends BaseDto {
    @ApiModelProperty(value = "当事人id", example = "1")
    private Integer partyId;
    @ApiModelProperty(value = "玩家id", example = "1")
    private Integer playerId;
    @ApiModelProperty(value = "名单类型", example = "1")
    private Integer listType;
}
