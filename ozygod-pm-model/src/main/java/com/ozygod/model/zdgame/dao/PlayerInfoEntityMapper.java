package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.PlayerAccountBO;
import com.ozygod.model.zdgame.bo.PlayerInfoBO;
import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.entity.PlayerInfoEntity;

import java.util.List;

public interface PlayerInfoEntityMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(PlayerInfoEntity record);

    int insertSelective(PlayerInfoEntity record);

    PlayerInfoEntity selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(PlayerInfoEntity record);

    int updateByPrimaryKey(PlayerInfoEntity record);

    /**
     * 查询玩家财富排行列表
     * @param dto
     * @return
     */
    List<PlayerInfoBO> listPlayerWealthRankByQry(PlayerAccountDto dto);

    /**
     * 查询玩家财富排行列表总数
     * @param dto
     * @return
     */
    int totalPlayerWealthRankByQry(PlayerAccountDto dto);

    /**
     * 查询未结算玩家列表
     * @param dto
     * @return
     */
    List<PlayerAccountBO> listDebtPlayerQry(PlayerAccountDto dto);

    /**
     * 查询未结算玩家列表总数
     * @param dto
     * @return
     */
    int totalDebtPlayerByQry(PlayerAccountDto dto);
}