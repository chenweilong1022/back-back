package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 经典牛牛vo
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:28
 */
@Data
public class JDNNGameConfigVo extends BaseGameConfigVo {


    /**
     * gold : [{"min":1100,"max":11000},{"min":20000,"max":100000},{"min":20000,"max":100000}]
     * stragy : 1
     * time : {"min":0,"max":60}
     * max_gold : {"min":600000,"max":600000}
     * logic : jdnn
     * fixBet : 100
     * join_delay : {"min":1,"max":10}
     * num : {"min":13,"max":13}
     */
    private BigDecimal stragy;
    private MinMaxVo time;
    private MinMaxVo max_gold;
    private String logic;
    private BigDecimal fixBet;
    private MinMaxVo join_delay;
    private MinMaxVo num;
    private List<MinMaxVo> gold;

}
