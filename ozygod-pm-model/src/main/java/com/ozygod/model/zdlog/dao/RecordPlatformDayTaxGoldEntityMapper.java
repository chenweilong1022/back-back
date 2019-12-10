package com.ozygod.model.zdlog.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdlog.bo.DailyGameTaxGoldCountBO;
import com.ozygod.model.zdlog.entity.RecordPlatformDayTaxGoldEntity;

import java.util.List;

public interface RecordPlatformDayTaxGoldEntityMapper {
    int insert(RecordPlatformDayTaxGoldEntity record);

    int insertSelective(RecordPlatformDayTaxGoldEntity record);

    /**
     * 查询指定游戏每日税收统计列表
     * @param dto
     * @return
     */
    List<DailyGameTaxGoldCountBO> listDailyGameTaxGoldCountByQry(PlatformDto dto);

    /**
     * 查询指定游戏每日税收统计列表总数
     * @param dto
     * @return
     */
    int totalDailyGameTaxGoldCountByQry(PlatformDto dto);
}