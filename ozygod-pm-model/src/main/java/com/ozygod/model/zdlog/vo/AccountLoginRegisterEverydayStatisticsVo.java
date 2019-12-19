package com.ozygod.model.zdlog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 14:21
 */
@Data
@Accessors(chain = true)
public class AccountLoginRegisterEverydayStatisticsVo {

    private List<Integer> counts;
    private String accountLoginWay;
}
