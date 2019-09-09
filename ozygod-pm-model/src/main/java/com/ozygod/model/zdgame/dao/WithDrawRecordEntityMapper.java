package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.PlayerWithDrawRecordBO;
import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.entity.WithDrawRecordEntity;

import java.util.List;

public interface WithDrawRecordEntityMapper {
    int insert(WithDrawRecordEntity record);

    int insertSelective(WithDrawRecordEntity record);

    /**
     * 查询玩家提现记录列表
     * @param dto
     * @return
     */
    List<PlayerWithDrawRecordBO> listPlayerWithDrawRecordByQry(PlayerAccountDto dto);

    /**
     * 查询玩家提现记录列表总数
     * @param dto
     * @return
     */
    int totalPlayerWithDrawRecordByQry(PlayerAccountDto dto);

    /**
     * 查询玩家提现总额
     * @param dto
     * @return
     */
    int getTotalPlayerWithDrawRecordByQry(PlayerAccountDto dto);
}