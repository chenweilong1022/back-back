package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;

import java.util.List;

/**
 * 深海捕鱼vo
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:28
 */
@Data
public class SHBYGameConfigVo extends BaseGameConfigVo {


    /**
     * baseScore : 10
     * num : {"min":1,"max":1}
     * max_gold : {"min":600000,"max":600000}
     * join_delay : {"min":1,"max":10}
     * time : {"min":0,"max":60}
     * gold : [{"min":1100,"max":11000},{"min":20000,"max":100000},{"min":20000,"max":100000}]
     * stragy : 1
     * logic : dtdw_shcs
     * fixBet : 100
     */
    private int baseScore;
    private MinMaxVo num;
    private MinMaxVo max_gold;
    private MinMaxVo join_delay;
    private MinMaxVo time;
    private int stragy;
    private String logic;
    private int fixBet;
    private List<MinMaxVo> gold;

}
