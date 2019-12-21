package com.ozygod.model.zdlog.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 16:48
 */
@Data
@Accessors(chain = true)
public class RechargeAmountStatisticsAllVo {


    private List<String> xAxis;

    private List<RechargeAmountStatisticsVo> rechargeAmountStatisticsVos;

    private int totalweixin;
    private int totalali;
    private int totalapple;
    private int totalPrice;

    @JSONField(format = "yyyy.MM.dd")
    private Date startTime;
    @JSONField(format = "yyyy.MM.dd")
    private Date endTime;

    public void add(RechargeAmountStatisticsVo rechargeAmountStatisticsVo) {
        if (CollUtil.isEmpty(rechargeAmountStatisticsVos)) {
            rechargeAmountStatisticsVos = new ArrayList<>();
        }
        rechargeAmountStatisticsVos.add(rechargeAmountStatisticsVo);
    }

    public String getTotalweixin() {
        return NumberUtil.decimalFormatMoney(this.totalweixin / 100);
    }

    public String getTotalali() {
        return NumberUtil.decimalFormatMoney(this.totalali / 100);
    }

    public String getTotalapple() {
        return NumberUtil.decimalFormatMoney(this.totalapple / 100);
    }

    public String getTotalPrice() {
        return NumberUtil.decimalFormatMoney(this.totalPrice / 100);
    }
}
