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
public enum AccountRegisterType implements BaseEnum {

    IOS(13,"ios","IOS注册"),
    ANDROID(14,"andorid","安卓注册");
//    FIVE(5,"充值金额(元)"),
//    SIX(6,"总充值成功率"),
//    SEVEN(7,"兑换金额(元)"),
//    EIGHT(8,"今日兑换率");


    private Integer key;
    private String value;

    private String registerType;

    public static final String DESCRIBE = EnumUtil.describe(AccountRegisterType.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface AccountRegisterTypeStr {

        Integer getAccountRegisterType();


        default String getAccountRegisterTypeStr() {
            return EnumUtil.queryValueByKey(getAccountRegisterType(), AccountRegisterType.values());
        }
    }

    AccountRegisterType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    AccountRegisterType(Integer key, String value, String registerType) {
        this.key = key;
        this.value = value;
        this.registerType = registerType;
    }

}
