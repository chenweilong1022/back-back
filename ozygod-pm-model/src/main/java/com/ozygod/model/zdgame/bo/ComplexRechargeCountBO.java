package com.ozygod.model.zdgame.bo;

import com.ozygod.base.bo.BaseBO;
import lombok.Data;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/20
 */
@Data
public class ComplexRechargeCountBO extends BaseBO {
    private RechargeCountBO rechargeCountBO;
    private List<DailyRechargeCountBO> dailyRechargeCountList;
}
