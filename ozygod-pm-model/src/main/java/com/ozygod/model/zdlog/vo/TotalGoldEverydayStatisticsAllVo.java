package com.ozygod.model.zdlog.vo;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 17:25
 */
@Data
@Accessors(chain = true)
public class TotalGoldEverydayStatisticsAllVo {

    private List<String> xAxis;
    private List<TotalGoldEverydayStatisticsVo> totalGoldEverydayStatisticsVos;

    @JSONField(format = "yyyy.MM.dd")
    private Date startTime;
    @JSONField(format = "yyyy.MM.dd")
    private Date endTime;

    /**
     * 玩家身上金币
     */
    @ApiModelProperty(required=false,value="玩家身上金币")
    private Long gold;
    /**
     * 玩家银行金币
     */
    @ApiModelProperty(required=false,value="玩家银行金币")
    private Long bankGold;
    /**
     * 玩家总金币
     */
    @ApiModelProperty(required=false,value="玩家总金币")
    private Long totalGold;

    public void add(TotalGoldEverydayStatisticsVo totalGoldEverydayStatisticsVo) {
        if (CollUtil.isEmpty(totalGoldEverydayStatisticsVos)) {
            totalGoldEverydayStatisticsVos = new ArrayList<>();
        }
        totalGoldEverydayStatisticsVos.add(totalGoldEverydayStatisticsVo);
    }
}
