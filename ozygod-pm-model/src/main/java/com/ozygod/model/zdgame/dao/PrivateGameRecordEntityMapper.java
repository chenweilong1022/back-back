package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.PrivateGameRecordBO;
import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.entity.PrivateGameRecordEntity;

import java.util.List;

public interface PrivateGameRecordEntityMapper {
    int insert(PrivateGameRecordEntity record);

    int insertSelective(PrivateGameRecordEntity record);


    /**
     * 玩家私人房游戏记录统计
     * @param dto
     * @return
     */
    List<PrivateGameRecordBO> listPrivateGameStatisticsByQry(PlayerAccountDto dto);

    /**
     * 玩家私人房游戏记录统计总数
     * @param dto
     * @return
     */
    int totalPrivateGameStatisticsByQry(PlayerAccountDto dto);


    /**
     * 玩家私人房游戏记录列表
     * @param dto
     * @return
     */
    List<PrivateGameRecordBO> listPlayerPrivateGameByQry(PlayerAccountDto dto);

    /**
     * 玩家私人房游戏记录总数
     * @param dto
     * @return
     */
    int totalPlayerPrivateGameByQry(PlayerAccountDto dto);
}