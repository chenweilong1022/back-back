package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * 游戏玩家在线走势按钮枚举
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum PlayerOnlineStatisticsButtons implements BaseEnum {

    ONE(1,"近一周"),
    TWO(2,"近两周"),
    THREE(3,"近一个月");

    private Integer key;
    private String value;

    public static final String DESCRIBE = "1:不是 2:是 ";


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface PlayerOnlineStatisticsButtonsStr {

        Integer getPlayerOnlineStatisticsButtons();


        default String getPlayerOnlineStatisticsButtonsStr() {
            return EnumUtil.queryValueByKey(getPlayerOnlineStatisticsButtons(), PlayerOnlineStatisticsButtons.values());
        }
    }

    PlayerOnlineStatisticsButtons(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
