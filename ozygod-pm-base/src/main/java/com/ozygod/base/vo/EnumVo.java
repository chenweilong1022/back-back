package com.ozygod.base.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 枚举类输出前端的实体类
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019/6/18
 */
@Data
@Accessors(chain = true)
public class EnumVo {
    private Integer key;
    private String value;
}
