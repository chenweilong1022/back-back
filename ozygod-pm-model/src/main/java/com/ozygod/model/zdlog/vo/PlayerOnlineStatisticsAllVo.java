package com.ozygod.model.zdlog.vo;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-17 15:47
 */
@Data
@Accessors(chain = true)
public class PlayerOnlineStatisticsAllVo {

    private List<PlayerOnlineStatisticsVo> playerOnlineStatisticsVos;

    @JSONField(format = "HH:mm")
    private List<DateTime> dateTimes;

    @JSONField(format = "yyyy.MM.dd")
    private Date startTime;
    @JSONField(format = "yyyy.MM.dd")
    private Date endTime;
}
