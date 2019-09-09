package com.ozygod.model.zdlog.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdlog.bo.DailyPlayersCountBO;
import com.ozygod.model.zdlog.entity.RecordPlatformDayPlayerEntity;
import java.util.Date;
import java.util.List;

public interface RecordPlatformDayPlayerEntityMapper {
    int deleteByPrimaryKey(Date recordTime);

    int insert(RecordPlatformDayPlayerEntity record);

    int insertSelective(RecordPlatformDayPlayerEntity record);

    RecordPlatformDayPlayerEntity selectByPrimaryKey(Date recordTime);

    int updateByPrimaryKeySelective(RecordPlatformDayPlayerEntity record);

    int updateByPrimaryKey(RecordPlatformDayPlayerEntity record);

    /**
     * 查询按日统计玩家人数列表
     * @param dto
     * @return
     */
    List<DailyPlayersCountBO> listDailyPlayersCountByQry(PlatformDto dto);

    /**
     * 查询按日统计玩家人数列表总数
     * @param dto
     * @return
     */
    int totalDailyPlayersCountByQry(PlatformDto dto);
}