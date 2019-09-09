package com.ozygod.model.zdspread.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.dto.BaseDto;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@Data
public class SpreadDto extends BaseDto {
    private Integer spreadUserId;
    private Integer playerId;
    private Integer giveGold;
    private Integer giveDiamond;
    @JSONField(format = "yyyy-MM-dd")
    private Date queryDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
}
