package com.ozygod.model.zdconfig.enums;

import cn.hutool.core.util.PinyinUtil;
import com.ozygod.base.enums.BaseEnum;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.model.zdconfig.vo.game.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 游戏枚举配置
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum GameConfig implements BaseEnum {

    SB(126,"色宝",new SBGameConfigVo(),"sbcard"),
    BJL(127,"百家乐", new BJLGameConfigVo(),"bjlcard"),
    LH(128,"龙虎",new LZHDGameConfigVo(),"lhcard"),
    BRNN(131,"百人牛牛",new BRNNGameConfigVo(),"brnncard"),
    TBNN(132,"通比牛牛",new TBNNGameConfigVo(),"tbnncard"),
    QZNN(133,"抢庄牛牛",new QZNNGameConfigVo(),"qznncard"),
    JDJL(142,"经典接龙",new JDJLGameConfigVo(),"jdjlcard"),
    JDSL(143,"经典扫雷",new JDSLGameConfigVo(),"jdslcard"),
    JDNN(144,"经典牛牛",new JDNNGameConfigVo(),"jdnncard"),
    DNTG(145,"大闹天宫",new DNTGGameConfigVo(),"dntgcard"),
    SHBY(148,"深海捕鱼",new SHBYGameConfigVo(),"shbycard"),
    JXLW(149,"九线拉王",null,"jxlwcard"),
    EBG(154,"二八杠",new EBGGameConfigVo(),"ebgcard");



//    public static void main(String[] args) {
//        List<String> values = EnumUtil.values(GameConfig.values());
//        for (String value : values) {
//            String firstLetters = "";
//            for (int i = 0;i < value.length();i++) {
//                char c = value.charAt(i);
//                char firstLetter = PinyinUtil.getFirstLetter(c);
//                firstLetters += firstLetter;
//            }
//            System.out.println(firstLetters.toUpperCase());
//        }
//    }

    private Integer key;
    private String value;
    private BaseGameConfigVo baseGameConfigVo;
    private String cardName;


    public static final String DESCRIBE = EnumUtil.describe(GameConfig.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface WithdrawOrderStateStr {

        Integer getWithdrawOrderState();


        default String getWithdrawOrderStateStr() {
            return EnumUtil.queryValueByKey(getWithdrawOrderState(), GameConfig.values());
        }
    }

    GameConfig(Integer key, String value,BaseGameConfigVo baseGameConfigVo,String cardName) {
        this.key = key;
        this.value = value;
        this.baseGameConfigVo = baseGameConfigVo;
        this.cardName = cardName;
    }

    public static GameConfig getByKey(Integer key) {
        GameConfig baseEnum1 = Arrays.stream(GameConfig.values()).filter(baseEnum -> baseEnum.getKey().equals(key)).findAny().orElse(null);
        return baseEnum1;
    }

}
