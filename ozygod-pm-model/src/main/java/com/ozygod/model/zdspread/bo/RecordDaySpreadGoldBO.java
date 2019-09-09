package com.ozygod.model.zdspread.bo;

import com.ozygod.model.zdspread.entity.RecordDaySpreadGoldEntity;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@Data
public class RecordDaySpreadGoldBO extends RecordDaySpreadGoldEntity {
    private String nickName;
    private String showName;
    private Long dayTaxGold;
    private Long daySpreadGold;
}
