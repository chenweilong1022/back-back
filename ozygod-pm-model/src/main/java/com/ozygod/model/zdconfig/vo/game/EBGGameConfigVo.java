package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 百家乐vo
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:24
 */
@NoArgsConstructor
@Data
public class EBGGameConfigVo extends BaseGameConfigVo {


    /**
     * bet : {"min":1000,"max":20000}
     * num : {"min":2,"max":4}
     * win : {"zhuangWin":45,"equal":10,"xianWin":45}
     * max_gold : {"min":6000000,"max":6000000}
     * join_delay : {"min":1,"max":10}
     * minIdleSeat : {"min":3,"max":6}
     * time : {"min":0,"max":60}
     * gold : [{"min":100000,"max":1000000},{"min":200000,"max":1000000},{"min":200000,"max":1000000}]
     * stragy : 1
     * logic : dtdw_ebg
     * zone : {"zhuangDui":10,"xianDui":10}
     */

    private MinMaxVo bet;
    private MinMaxVo num;
    private WinVo win;
    private MinMaxVo max_gold;
    private MinMaxVo join_delay;
    private MinMaxVo minIdleSeat;
    private MinMaxVo time;
    private int stragy;
    private String logic;
    private ZoneVo zone;
    private List<MinMaxVo> gold;

}
