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
@Data
public class SBGameConfigVo extends BaseGameConfigVo {


    /**
     * bet : {"min":1000,"max":20000}
     * num : {"min":2,"max":4}
     * max_gold : {"min":600000,"max":600000}
     * join_delay : {"min":1,"max":10}
     * minIdleSeat : {"min":3,"max":6}
     * time : {"min":0,"max":60}
     * gold : [{"min":10000,"max":10000},{"min":20000,"max":100000},{"min":20000,"max":100000}]
     * betZone : {"point3":1,"point4":1,"point2":1,"sum13":1,"sum16":1,"sum7":1,"sum15":1,"baozi6":1,"baozi1":1,"sum6":1,"point1":1,"sum9":1,"point5":1,"baozi5":1,"baozi2":1,"sum12":1,"sum8":1,"sum4":1,"baozi3":1,"sum11":1,"big":40,"baozi":6,"small":40,"baozi4":1,"sum5":1,"sum17":1,"sum10":1,"point6":1,"sum14":1}
     * logic : sb
     * stragy : 1
     */

    private MinMaxVo bet;
    private MinMaxVo num;
    private MinMaxVo max_gold;
    private MinMaxVo join_delay;
    private MinMaxVo minIdleSeat;
    private MinMaxVo time;
    private BetZoneVO betZone;
    private String logic;
    private int stragy;
    private List<MinMaxVo> gold;

}
