package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-04-10 10:59
 */
@Data
public class TBNNGameConfigVo  extends BaseGameConfigVo {
    /**
     * bet : {"min":1000,"max":20000}
     * robot_give_upper : 100000000
     * join_delay : {"min":1,"max":10}
     * max_banker_win_gold : 100000000
     * time : {"min":0,"max":60}
     * gold : {"min":10000000,"max":500000000}
     * logic : tbnn
     * robot_min_gold : 10000000
     * off_banker_gold : 50000000
     * max_ask_bets_delay : 250
     * max_gold : {"min":600000,"max":600000}
     * robot_take_lower : 300000000
     * stragy : 2
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
    private int robot_give_upper;
    private MinMaxVo join_delay;
    private int max_banker_win_gold;
    private MinMaxVo time;
    private MinMaxVo gold;
    private String logic;
    private int robot_min_gold;
    private int off_banker_gold;
    private int max_ask_bets_delay;
    private MinMaxVo max_gold;
    private int robot_take_lower;
    private int stragy;
    private int min_banker_req_delay;
    private MinMaxVo num;
    private int max_on_banker_cnt;
    private int max_banker_req_delay;
    private int min_on_banker_cnt;
    private int min_ask_bets_delay;
    private int robot_give_lower;
    private int robot_max_gold;
    private int act_human_cnt;
    private int robot_take_upper;

}
