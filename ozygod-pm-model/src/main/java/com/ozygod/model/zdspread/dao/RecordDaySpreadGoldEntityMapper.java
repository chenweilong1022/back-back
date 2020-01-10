package com.ozygod.model.zdspread.dao;

import com.ozygod.model.zdspread.bo.RecordDaySpreadGoldBO;
import com.ozygod.model.zdspread.dto.SpreadDto;
import com.ozygod.model.zdspread.entity.RecordDaySpreadGoldEntity;

import java.util.List;

public interface RecordDaySpreadGoldEntityMapper {
    int insert(RecordDaySpreadGoldEntity record);

    int insertSelective(RecordDaySpreadGoldEntity record);

    /**
     * 按日汇总金币提成记录
     * @param dto
     * @return
     */
    List<RecordDaySpreadGoldBO> listDailyCountSpreadGoldRecordByQry(SpreadDto dto);

    /**
     * 查询推广员金币提成记录列表
     * @param dto
     * @return
     */
    List<RecordDaySpreadGoldBO> listRecordDaySpreadGoldByQry(SpreadDto dto);

    /**
     * 查询推广员金币提成记录总数
     * @param dto
     * @return
     */
    int totalRecordDaySpreadGoldByQry(SpreadDto dto);
}
