package com.ozygod.model.zdspread.dao;

import com.ozygod.model.zdspread.bo.RecordSpreadGiveDiamondBO;
import com.ozygod.model.zdspread.dto.SpreadDto;
import com.ozygod.model.zdspread.entity.RecordSpreadGiveDiamondEntity;

import java.util.List;

public interface RecordSpreadGiveDiamondEntityMapper {
    int insert(RecordSpreadGiveDiamondEntity record);

    int insertSelective(RecordSpreadGiveDiamondEntity record);

    /**
     * 查询推广员钻石赠送记录列表
     * @param dto
     * @return
     */
    List<RecordSpreadGiveDiamondBO> listRecordSpreadGiveDiamondByQry(SpreadDto dto);

    /**
     * 查询推广员钻石赠送记录总数
     * @param dto
     * @return
     */
    int totalRecordSpreadGiveDiamondByQry(SpreadDto dto);
}
