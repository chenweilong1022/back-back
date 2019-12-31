package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * 渠道
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum AccountRegisterChannel implements BaseEnum {

//    GUEST(1,"guest","快速登录"),
//    OTHER(2,"非guest","手机登录");
        C3521(5,"3521","渠道3521"),
        COTHER(6,"其他渠道","其他渠道");
//    SIX(6,"总充值成功率"),
//    SEVEN(7,"兑换金额(元)"),
//    EIGHT(8,"今日兑换率");


    private Integer key;
    private String value;

    private String registerChannel;


    public static final String DESCRIBE = EnumUtil.describe(AccountRegisterChannel.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface AccountRegisterChannelStr {

        Integer getAccountRegisterChannel();


        default String getAccountRegisterChannelStr() {
            return EnumUtil.queryValueByKey(getAccountRegisterChannel(), AccountRegisterChannel.values());
        }
    }

    AccountRegisterChannel(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    AccountRegisterChannel(Integer key, String value, String registerChannel) {
        this.key = key;
        this.value = value;
        this.registerChannel = registerChannel;
    }

}
