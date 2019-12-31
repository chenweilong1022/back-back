package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * 首页卡片枚举
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum IndexCard implements BaseEnum {

    ONE(1,"注册用户数","people"),
    TWO(2,"在线用户数","example"),
    THREE(3,"登录用户数","user"),
    FOUR(4,"充值用户数","theme"),
    FIVE(5,"充值金额(元)","money"),
    SIX(6,"总充值成功率","component"),
    SEVEN(7,"兑换金额(元)","money"),
    EIGHT(8,"今日兑换率","component");


    private Integer key;
    private String value;

    private String icon;

    public static final String DESCRIBE = EnumUtil.describe(IndexCard.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface IndexCardStr {

        Integer getIndexCardStr();


        default String getIndexCardStrStr() {
            return EnumUtil.queryValueByKey(getIndexCardStr(), IndexCard.values());
        }
    }

    IndexCard(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    IndexCard(Integer key, String value,String icon) {
        this.key = key;
        this.value = value;
        this.icon = icon;
    }

}
