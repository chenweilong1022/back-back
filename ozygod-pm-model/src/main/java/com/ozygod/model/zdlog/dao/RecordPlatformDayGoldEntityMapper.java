package com.ozygod.model.zdlog.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdlog.bo.DailyGoldCountBO;
import com.ozygod.model.zdlog.entity.RecordPlatformDayGoldEntity;
import java.util.Date;
import java.util.List;

public interface RecordPlatformDayGoldEntityMapper {
    int deleteByPrimaryKey(Date recordTime);

    int insert(RecordPlatformDayGoldEntity record);

    int insertSelective(RecordPlatformDayGoldEntity record);

    RecordPlatformDayGoldEntity selectByPrimaryKey(Date recordTime);

    int updateByPrimaryKeySelective(RecordPlatformDayGoldEntity record);

    int updateByPrimaryKey(RecordPlatformDayGoldEntity record);

    /**
     * 查询按日统计财富列表
     * @param dto
     * @return
     */
    List<DailyGoldCountBO> listDailyGoldCountByQry(PlatformDto dto);

    /**
     * 查询按日统计财富列表总数
     * @param dto
     * @return
     */
    int totalDailyGoldCountByQry(PlatformDto dto);

    /**
     * 查询间隔财富统计总和
     * @param dto
     * @return
     */
    DailyGoldCountBO getDailyGoldCountSumByQry(PlatformDto dto);
}