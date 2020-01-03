package com.ozygod.base.enums;

import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.utils.EnumUtil;
import lombok.Getter;

import java.util.Arrays;

/**
 * 渠道分类
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/17
 */
@Getter
public enum AccounChannelClassify implements BaseEnum {

    ALL_GENERATIONS(1,"全民代",0),
    SELF_REGISTRATION(2,"自注册",0);


    private Integer key;
    private String value;

    private Integer agentUserId;


    public static final String DESCRIBE = EnumUtil.describe(AccounChannelClassify.values());


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface AccountRegisterChannelStr {

        Integer getAccountRegisterChannel();


        default String getAccountRegisterChannelStr() {
            return EnumUtil.queryValueByKey(getAccountRegisterChannel(), AccounChannelClassify.values());
        }
    }

    AccounChannelClassify(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static AccounChannelClassify getByKey(Integer key) {
        AccounChannelClassify baseEnum1 = Arrays.stream(AccounChannelClassify.values()).filter(baseEnum -> baseEnum.getKey().equals(key)).findAny().orElse(null);
        return ObjectUtil.isNotNull(baseEnum1) ? baseEnum1 : ALL_GENERATIONS;
    }

    AccounChannelClassify(Integer key, String value, Integer agentUserId) {
        this.key = key;
        this.value = value;
        this.agentUserId = agentUserId;
    }

}
