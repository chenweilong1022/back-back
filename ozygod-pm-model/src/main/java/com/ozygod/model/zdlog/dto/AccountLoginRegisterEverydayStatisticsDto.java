package com.ozygod.model.zdlog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 15:27
 */
@Data
@Accessors(chain = true)
public class AccountLoginRegisterEverydayStatisticsDto {

    private Integer key;

    private Integer type;
}
