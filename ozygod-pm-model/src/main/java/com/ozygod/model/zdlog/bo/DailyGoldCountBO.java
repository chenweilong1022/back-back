package com.ozygod.model.zdlog.bo;

import com.ozygod.model.zdlog.entity.RecordPlatformDayGoldEntity;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/26
 */
@Data
public class DailyGoldCountBO extends RecordPlatformDayGoldEntity {
    private Long totalTax;
    private Long totalInGold;
}
