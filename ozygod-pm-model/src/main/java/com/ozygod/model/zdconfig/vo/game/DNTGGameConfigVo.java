package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 大闹天宫vo
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:28
 */
@NoArgsConstructor
@Data
public class DNTGGameConfigVo extends BaseGameConfigVo {


    /**
//     * num : {"min":3,"max":3}
//     * max_gold : {"min":600000,"max":600000}
//     * fixBet : 100
//     * join_delay : {"min":1,"max":10}
     * baseScore : 10
//     * time : {"min":0,"max":60}
//     * gold : [{"min":1100,"max":11000},{"min":20000,"max":100000},{"min":20000,"max":100000}]
     * logic : dtdw_dntg
     * stragy : 1
     */
    private MinMaxVo num;
    private MinMaxVo max_gold;
    private BigDecimal fixBet;
    private MinMaxVo join_delay;
    private BigDecimal baseScore;
    private MinMaxVo time;
    private String logic;
    private BigDecimal stragy;
    private List<MinMaxVo> gold;


}
