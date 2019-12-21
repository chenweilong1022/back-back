package com.ozygod.model.zdlog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 17:48
 */
@Data
@Accessors(chain = true)
public class RechargeAmountStatisticsDto {

    private Integer key;

    private String payChannel;
}
