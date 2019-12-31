package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * 登录注册渠道类型枚举
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum AccountLoginType implements BaseEnum {

    IOS(3,"ios","IOS登录"),
    ANDROID(4,"andorid","安卓登录");
//    FIVE(5,"充值金额(元)"),
//    SIX(6,"总充值成功率"),
//    SEVEN(7,"兑换金额(元)"),
//    EIGHT(8,"今日兑换率");


    private Integer key;
    private String value;

    private String loginType;

    public static final String DESCRIBE = EnumUtil.describe(AccountLoginType.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface IndexCardStr {

        Integer getAccountLoginRegisterType();


        default String getAccountLoginRegisterTypeStr() {
            return EnumUtil.queryValueByKey(getAccountLoginRegisterType(), AccountLoginType.values());
        }
    }

    AccountLoginType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    AccountLoginType(Integer key, String value,String loginType) {
        this.key = key;
        this.value = value;
        this.loginType = loginType;
    }

}
