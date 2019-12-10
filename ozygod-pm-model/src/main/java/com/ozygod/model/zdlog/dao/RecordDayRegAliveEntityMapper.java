package com.ozygod.model.zdlog.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdlog.bo.DayRegAliveCountBO;
import com.ozygod.model.zdlog.entity.RecordDayRegAliveEntity;

import java.util.List;

public interface RecordDayRegAliveEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RecordDayRegAliveEntity record);

    int insertSelective(RecordDayRegAliveEntity record);

    RecordDayRegAliveEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RecordDayRegAliveEntity record);

    int updateByPrimaryKey(RecordDayRegAliveEntity record);

    /**
     * 注册留存率每日统计列表
     * @param dto
     * @return
     */
    List<DayRegAliveCountBO> listDailyRegAliveCountByQry(PlatformDto dto);

    /**
     * 注册留存率每日统计列表总数
     * @param dto
     * @return
     */
    int totalDailyRegAliveCountByQry(PlatformDto dto);
}