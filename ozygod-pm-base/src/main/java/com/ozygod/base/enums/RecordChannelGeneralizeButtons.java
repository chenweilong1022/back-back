package com.ozygod.base.enums;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

import java.util.Arrays;

/**
 * 渠道统计按钮枚举
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum RecordChannelGeneralizeButtons implements BaseEnum {

    ONE(1,"今日_t(%s)",DateTime.now()),
    TWO(2,"昨日_y(%s)", DateUtil.offsetDay(DateTime.now(),-1)),
    THREE(3,"前日_q(%s)",DateUtil.offsetDay(DateTime.now(),-2)),
    FOUR(4,"全部_o(%s)",null);

    private Integer key;
    private String value;

    private DateTime dateTime;

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

    public static RecordChannelGeneralizeButtons getByKey(Integer key) {
        RecordChannelGeneralizeButtons baseEnum1 = Arrays.stream(RecordChannelGeneralizeButtons.values()).filter(baseEnum -> baseEnum.getKey().equals(key)).findAny().orElse(null);
        return ObjectUtil.isNotNull(baseEnum1) ? baseEnum1 : ONE;
    }

    RecordChannelGeneralizeButtons(Integer key, String value,DateTime dateTime) {
        this.key = key;
        this.value = value;
        this.dateTime = dateTime;
    }
}
