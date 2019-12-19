package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * 登录方式
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum AccountLoginWay implements BaseEnum {

    GUEST(1,"guest","快速登录"),
    OTHER(2,"非guest","手机登录");
//    FIVE(5,"充值金额(元)"),
//    SIX(6,"总充值成功率"),
//    SEVEN(7,"兑换金额(元)"),
//    EIGHT(8,"今日兑换率");


    private Integer key;
    private String value;

    private String loginWay;


    public static final String DESCRIBE = "1:不是 2:是 ";


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface IndexCardStr {

        Integer getAccountLoginRegisterType();


        default String getAccountLoginRegisterTypeStr() {
            return EnumUtil.queryValueByKey(getAccountLoginRegisterType(), AccountLoginWay.values());
        }
    }

    AccountLoginWay(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    AccountLoginWay(Integer key, String value,String loginWay) {
        this.key = key;
        this.value = value;
        this.loginWay = loginWay;
    }

}
