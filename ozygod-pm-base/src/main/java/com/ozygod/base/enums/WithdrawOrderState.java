package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

import java.util.stream.Collectors;

/**
 * 提现状态枚举
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum WithdrawOrderState implements BaseEnum {

    ZERO(0,"未处理"),
    ONE(1,"已审核"),
    TWO(2,"已打款"),
    THREE(3,"已取消");


    private Integer key;
    private String value;


    public static final String DESCRIBE = EnumUtil.describe(WithdrawOrderState.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface WithdrawOrderStateStr {

        Integer getWithdrawOrderState();


        default String getWithdrawOrderStateStr() {
            return EnumUtil.queryValueByKey(getWithdrawOrderState(), WithdrawOrderState.values());
        }
    }

    WithdrawOrderState(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
