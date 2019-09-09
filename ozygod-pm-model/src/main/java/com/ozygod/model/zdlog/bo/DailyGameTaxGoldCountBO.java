package com.ozygod.model.zdlog.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.bo.BaseBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/26
 */
@Data
public class DailyGameTaxGoldCountBO extends BaseBO {
    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;
    private Integer gameId;
    @ApiModelProperty("税收总计")
    private Long totalTax;
    @ApiModelProperty("游戏玩家")
    private String gameName;
}
