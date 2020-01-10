package com.ozygod.model.zdspread.dao;

import com.ozygod.model.zdspread.bo.DailyGiveDiamondBO;
import com.ozygod.model.zdspread.dto.SpreadDto;
import com.ozygod.model.zdspread.entity.SpreadDiamondAddRecordEntity;

import java.util.List;

public interface SpreadDiamondAddRecordEntityMapper {
    int insert(SpreadDiamondAddRecordEntity record);

    int insertSelective(SpreadDiamondAddRecordEntity record);

    /**
     * 查询钻石提成记录
     * @param dto
     * @return
     */
    List<SpreadDiamondAddRecordEntity> listDiamondAddRecordByQry(SpreadDto dto);

    /**
     * 查询钻石提成记录
     * @param dto
     * @return
     */
    int totalDiamondAddRecordByQry(SpreadDto dto);


    /**
     * 查询钻石提成记录
     * @param dto
     * @return
     */
    List<DailyGiveDiamondBO> listDailyCountGiveDiamondByQry(SpreadDto dto);
}
