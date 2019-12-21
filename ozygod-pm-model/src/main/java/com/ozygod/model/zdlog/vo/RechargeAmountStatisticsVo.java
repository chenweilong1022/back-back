package com.ozygod.model.zdlog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 16:48
 */
@Data
@Accessors(chain = true)
public class RechargeAmountStatisticsVo {
    /**
     * 充值来源
     */
    private String rechargeSource;
    /**
     * 金额
     */
    private List<Double> moneys;
}
