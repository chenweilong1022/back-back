package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 经典接龙vo
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:28
 */
@Data
public class JDJLGameConfigVo extends BaseGameConfigVo {

    /**
//     * num : {"min":13,"max":13}
//     * time : {"min":0,"max":60}
//     * gold : [{"min":1100,"max":11000},{"min":20000,"max":100000},{"min":20000,"max":100000}]
//     * max_gold : {"min":600000,"max":600000}
     * logic : jdjl
//     * join_delay : {"min":1,"max":10}
     * stragy : 1
     */
    private MinMaxVo num;
    private MinMaxVo time;
    private MinMaxVo max_gold;
    private String logic;
    private MinMaxVo join_delay;
    private BigDecimal stragy;
    private List<MinMaxVo> gold;

}
