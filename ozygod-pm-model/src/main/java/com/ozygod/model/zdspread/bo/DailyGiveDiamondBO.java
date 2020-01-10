package com.ozygod.model.zdspread.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.base.bo.BaseBO;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/10/30
 */
@Data
public class DailyGiveDiamondBO extends BaseBO {
    @JSONField(format = "yyyy-MM-dd")
    private Date recordTime;
    private long totalDiamond;
    private long totalOrderDiamond;
}
