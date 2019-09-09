package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.PlatformOverviewBO;
import com.ozygod.model.common.dto.PlatformDto;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/20
 */
public interface GameProcedureMapper {
    /**
     * 查询平台概况
     * @return
     */
    PlatformOverviewBO getPlatformOverview();

    /**
     * 查询按日充值统计列表
     * @param dto
     * @return
     */
    List<List<?>> listDailyRechargeCountByQry(PlatformDto dto);
}
