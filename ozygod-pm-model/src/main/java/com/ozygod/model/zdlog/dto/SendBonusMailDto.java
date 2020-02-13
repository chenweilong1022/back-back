package com.ozygod.model.zdlog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-12 17:37
 */
@Data
@Accessors(chain = true)
public class SendBonusMailDto {
    private long userid;
    private long bonus;
    private String msg;
    private String reason;
}
