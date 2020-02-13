package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 百人牛牛vo
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:28
 */
@NoArgsConstructor
@Data
public class BRNNGameConfigVo extends BaseGameConfigVo {


    /**
     * bet : {"min":1000,"max":20000}
     * robot_give_upper : 100000000
     * join_delay : {"min":1,"max":10}
     * max_banker_win_gold : 100000000
     * time : {"min":0,"max":60}
     * gold : {"min":10000000,"max":500000000}
     * logic : nn100
     * robot_min_gold : 10000000
     * off_banker_gold : 50000000
     * max_ask_bets_delay : 250
     * max_gold : {"min":600000,"max":600000}
     * robot_take_lower : 300000000
     * stragy : 1
     * min_banker_req_delay : 100
     * num : {"min":2,"max":4}
     * max_on_banker_cnt : 15
     * max_banker_req_delay : 200
     * min_on_banker_cnt : 5
     * min_ask_bets_delay : 150
     * robot_give_lower : 50000000
     * robot_max_gold : 500000000
     * act_human_cnt : 0
     * robot_take_upper : 400000000
     */

    private MinMaxVo bet;
    private BigDecimal robot_give_upper;
    private MinMaxVo join_delay;
    private BigDecimal max_banker_win_gold;
    private MinMaxVo time;
    private MinMaxVo gold;
    private String logic;
    private BigDecimal robot_min_gold;
    private BigDecimal off_banker_gold;
    private BigDecimal max_ask_bets_delay;
    private MinMaxVo max_gold;
    private BigDecimal robot_take_lower;
    private BigDecimal stragy;
    private BigDecimal min_banker_req_delay;
    private MinMaxVo num;
    private BigDecimal max_on_banker_cnt;
    private BigDecimal max_banker_req_delay;
    private BigDecimal min_on_banker_cnt;
    private BigDecimal min_ask_bets_delay;
    private BigDecimal robot_give_lower;
    private BigDecimal robot_max_gold;
    private BigDecimal act_human_cnt;
    private BigDecimal robot_take_upper;

}
