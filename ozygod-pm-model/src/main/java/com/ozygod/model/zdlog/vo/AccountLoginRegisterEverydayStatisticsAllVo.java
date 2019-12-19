package com.ozygod.model.zdlog.vo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 14:22
 */
@Data
@Accessors(chain = true)
public class AccountLoginRegisterEverydayStatisticsAllVo {

    @JSONField(format = "yyyy.MM.dd")
    private Date startTime;
    @JSONField(format = "yyyy.MM.dd")
    private Date endTime;
    @JSONField(format = "yyyyMMdd")
    private List<DateTime> dateTimes;
    private List<AccountLoginRegisterEverydayStatisticsVo> accountLoginEverydayStatisticsVos;

    public void add(AccountLoginRegisterEverydayStatisticsVo accountLoginRegisterEverydayStatisticsVo) {
        if (ObjectUtil.isNull(accountLoginEverydayStatisticsVos)) {
            accountLoginEverydayStatisticsVos = new ArrayList<>();
        }
        accountLoginEverydayStatisticsVos.add(accountLoginRegisterEverydayStatisticsVo);
    }
}
