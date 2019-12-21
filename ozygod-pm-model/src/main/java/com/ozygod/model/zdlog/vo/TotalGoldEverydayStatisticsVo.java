package com.ozygod.model.zdlog.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 17:25
 */
@Data
@Accessors(chain = true)
public class TotalGoldEverydayStatisticsVo {

    /**
     * 金币来源
     */
    private String goldSource;
    /**
     * 金币
     */
    private List<Long> golds;

}
