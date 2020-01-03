package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * 渠道统计按钮没救
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum RecordChannelGeneralizeButtons implements BaseEnum {

    ONE(1,"今日_t(%s)"),
    TWO(2,"昨日_y(%s)"),
    THREE(3,"前日_q(%s)"),
    FOUR(4,"全部_o(%s)");

    private Integer key;
    private String value;

    public static final String DESCRIBE = EnumUtil.describe(RecordChannelGeneralizeButtons.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface PlayerOnlineStatisticsButtonsStr {

        Integer getPlayerOnlineStatisticsButtons();


        default String getPlayerOnlineStatisticsButtonsStr() {
            return EnumUtil.queryValueByKey(getPlayerOnlineStatisticsButtons(), RecordChannelGeneralizeButtons.values());
        }
    }

    RecordChannelGeneralizeButtons(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
