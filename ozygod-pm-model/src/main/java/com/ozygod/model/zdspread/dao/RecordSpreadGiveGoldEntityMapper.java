package com.ozygod.model.zdspread.dao;

import com.ozygod.model.zdspread.bo.RecordSpreadGiveGoldBO;
import com.ozygod.model.zdspread.dto.SpreadDto;
import com.ozygod.model.zdspread.entity.RecordSpreadGiveGoldEntity;

import java.util.List;

public interface RecordSpreadGiveGoldEntityMapper {
    int insert(RecordSpreadGiveGoldEntity record);

    int insertSelective(RecordSpreadGiveGoldEntity record);

    /**
     * 查询推广员金币赠送记录列表
     * @param dto
     * @return
     */
    List<RecordSpreadGiveGoldBO> listRecordSpreadGiveGoldByQry(SpreadDto dto);

    /**
     * 查询推广员金币赠送记录总数
     * @param dto
     * @return
     */
    int totalRecordSpreadGiveGoldByQry(SpreadDto dto);
}
