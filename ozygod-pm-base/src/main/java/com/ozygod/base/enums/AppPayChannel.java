package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * app支付渠道
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 14:03
 */
@Getter
public enum AppPayChannel implements BaseEnum {

    ALIPAY(1,"ALIPAY","支付宝"),
    WEIXIN(2,"WEIXIN","微信"),
    APPLE(3,"APPLE","苹果");

    private Integer key;
    private String value;

    private String payChannel;
    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface AppPayChannelStr {

        Integer getAppPayChannel();


        default String getAppPayChannelStr() {
            return EnumUtil.queryValueByKey(getAppPayChannel(), AccountLoginWay.values());
        }
    }

    AppPayChannel(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    AppPayChannel(Integer key, String value,String payChannel) {
        this.key = key;
        this.value = value;
        this.payChannel = payChannel;
    }
}
