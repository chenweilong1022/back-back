package com.ozygod.base.enums;

import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

/**
 * rediskey 枚举
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum RedisKeys implements BaseEnum {

    // 总兑换数的key
    ONE(1,"platform:totalBonus"),
    //    消息中心的key
    MESSAGECENTER(2,"messagecenter:"),
    // 发送消息失败的
    MESSAGECENTERSENDERROR(3,"messagecenter:senderror");


    private Integer key;
    private String value;

    public static final String DESCRIBE = EnumUtil.describe(RedisKeys.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface RedisKeysStr {

        Integer getRedisKeys();


        default String getRedisKeysStr() {
            return EnumUtil.queryValueByKey(getRedisKeys(), IndexCard.values());
        }
    }

    RedisKeys(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
