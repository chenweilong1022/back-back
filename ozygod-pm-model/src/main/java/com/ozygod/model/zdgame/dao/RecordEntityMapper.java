package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.entity.RecordEntity;
import com.ozygod.model.zdgame.entity.RecordEntityKey;
import com.ozygod.model.zdgame.bo.RecordBO;

import java.util.List;

public interface RecordEntityMapper {
    int deleteByPrimaryKey(RecordEntityKey key);

    int insert(RecordEntity record);

    int insertSelective(RecordEntity record);

    RecordEntity selectByPrimaryKey(RecordEntityKey key);

    int updateByPrimaryKeySelective(RecordEntity record);

    int updateByPrimaryKey(RecordEntity record);

    /**
     * 查询玩家输赢记录
     * @param dto
     * @return
     */
    List<RecordBO> listPlayerGameRecordByQry(PlayerAccountDto dto);
}