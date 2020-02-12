package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 龙争虎斗vo
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:28
 */
@Data
public class LZHDGameConfigVo extends BaseGameConfigVo {

    /**
//     * bet : {"min":1000,"max":20000}
     * stragy : 1
//     * win : {"xianWin":45,"zhuangWin":45,"equal":10}
//     * zone : {"zhuangDui":10,"xianDui":10}
//     * join_delay : {"min":1,"max":10}
//     * minIdleSeat : {"min":3,"max":6}
//     * time : {"min":0,"max":60}
     * gold : [{"min":10000,"max":10000},{"min":20000,"max":100000},{"min":20000,"max":100000}]
     * max_gold : {"min":600000,"max":600000}
     * logic : lh
//     * num : {"min":2,"max":4}
     */
    private MinMaxVo bet;
    private BigDecimal stragy;
    private WinVo win;
    private ZoneVo zone;
    private MinMaxVo join_delay;
    private MinMaxVo minIdleSeat;
    private MinMaxVo time;
    private MinMaxVo max_gold;
    private String logic;
    private MinMaxVo num;
    private List<MinMaxVo> gold;

}
