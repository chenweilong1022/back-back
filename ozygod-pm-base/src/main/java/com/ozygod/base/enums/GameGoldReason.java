package com.ozygod.base.enums;

import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

import java.util.Arrays;

/**
 * 彩金记录
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 14:03
 */
@Getter
public enum GameGoldReason implements BaseEnum {

    ONE(1,"签到奖励",0),
    TWO(2,"首充返现",0),
    THREE(3,"充值返现",0),
    FOUR(4,"邀请有礼",0),
    FIVE(5,"代理奖上奖",0),
    SIX(6,"每日闯关奖",0);

    private Integer key;
    private String value;

    private Integer enabled;

    public static final String DESCRIBE = EnumUtil.describe(GameGoldReason.values());

    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface AppPayChannelStr {

        Integer getAppPayChannel();


        default String getAppPayChannelStr() {
            return EnumUtil.queryValueByKey(getAppPayChannel(), AccountLoginWay.values());
        }
    }

    GameGoldReason(Integer key, String value,Integer enabled) {
        this.key = key;
        this.value = value;
        this.enabled = enabled;
    }

    public static GameGoldReason getByKey(Integer key) {
        GameGoldReason baseEnum1 = Arrays.stream(GameGoldReason.values()).filter(baseEnum -> baseEnum.getKey().equals(key)).findAny().orElse(null);
        return baseEnum1;
    }
}
