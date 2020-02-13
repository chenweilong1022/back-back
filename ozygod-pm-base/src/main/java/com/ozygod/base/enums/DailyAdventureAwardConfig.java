package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 每日闯关将配置
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum DailyAdventureAwardConfig implements BaseEnum {

    ONE(1,"第一关",BigDecimal.valueOf(2000),BigDecimal.valueOf(8)),
    TWO(2,"第二关",BigDecimal.valueOf(5000),BigDecimal.valueOf(18)),
    THREE(3,"第三关",BigDecimal.valueOf(10000),BigDecimal.valueOf(58)),
    FOUR(4,"第四关",BigDecimal.valueOf(20000),BigDecimal.valueOf(88)),
    FIVE(5,"第五关",BigDecimal.valueOf(50000),BigDecimal.valueOf(188)),
    SIX(6,"第六关",BigDecimal.valueOf(100000),BigDecimal.valueOf(388)),
    SEVEN(7,"第七关",BigDecimal.valueOf(500000),BigDecimal.valueOf(888)),
    EIGHT(8,"第八关",BigDecimal.valueOf(1000000),BigDecimal.valueOf(1888)),
    NINE(9,"第九关",BigDecimal.valueOf(3000000),BigDecimal.valueOf(3888));

    private Integer key;
    private String value;
    //有效投注
    private BigDecimal effectiveBetting;
    //奖励
    private BigDecimal reward;


    public static final String DESCRIBE = EnumUtil.describe(DailyAdventureAwardConfig.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface WithdrawOrderStateStr {

        Integer getWithdrawOrderState();


        default String getWithdrawOrderStateStr() {
            return EnumUtil.queryValueByKey(getWithdrawOrderState(), DailyAdventureAwardConfig.values());
        }
    }

    DailyAdventureAwardConfig(Integer key, String value,BigDecimal effectiveBetting,BigDecimal reward) {
        this.key = key;
        this.value = value;
        this.effectiveBetting = effectiveBetting;
        this.reward = reward;
    }


    /**
     * 获取当前可以领取的关卡
     * @param currentBetting 当前总投注金额
     * @param alreadyKeys 已经领取的关卡
     * @return
     */
    public static List<DailyAdventureAwardConfig> getAccessibleAward(Integer currentBetting,List<Integer> alreadyKeys) {
        List<DailyAdventureAwardConfig> dailyAdventureAwardConfigs = new ArrayList<>();
        DailyAdventureAwardConfig[] values = values();
        for (DailyAdventureAwardConfig value : values) {
            if (currentBetting > value.getEffectiveBetting().multiply(BigDecimal.valueOf(100)).intValue() && !alreadyKeys.contains(value.getKey())) {
                dailyAdventureAwardConfigs.add(value);
            }
        }
        return dailyAdventureAwardConfigs;
    }

}
