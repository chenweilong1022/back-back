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
public enum HttpErrorStr implements BaseEnum {

    //链接超时
    ONE(1,"connect timed out");

    private Integer key;
    private String value;

    public static final String DESCRIBE = "1:不是 2:是 ";


    /**
     * 内部接口,实现该接口可获得中文描述
     */
    public interface HttpErrorStrStr {

        Integer getHttpErrorStr();


        default String getHttpErrorStrStr() {
            return EnumUtil.queryValueByKey(getHttpErrorStr(), IndexCard.values());
        }
    }

    HttpErrorStr(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
