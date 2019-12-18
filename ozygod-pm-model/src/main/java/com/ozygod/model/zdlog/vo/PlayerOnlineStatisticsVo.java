package com.ozygod.model.zdlog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-17 14:15
 */
@Data
@Accessors(chain = true)
public class PlayerOnlineStatisticsVo {

    private String date;
    private List<Integer> list;

}
