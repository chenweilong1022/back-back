package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * 彩金记录
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 14:03
 */
@Getter
public enum GameGoldReason implements BaseEnum {

    ONE(1,"签到奖励"),
    TWO(2,"首充返现"),
    THREE(3,"充值返现");

    private Integer key;
    private String value;

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

    GameGoldReason(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
