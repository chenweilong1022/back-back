package com.ozygod.model.zdlog.dao;

import com.ozygod.model.zdlog.bo.PlayerGameDiamondLogBO;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdlog.entity.GameDiamondEntity;

import java.util.List;

public interface GameDiamondEntityMapper {
    int insert(GameDiamondEntity record);

    int insertSelective(GameDiamondEntity record);

    /**
     * 查询玩家游戏日志列表
     * @param dto
     * @return
     */
    List<PlayerGameDiamondLogBO> listPlayerGameLogByQry(PlayerLogDto dto);

    /**
     * 查询玩家游戏日志列表总数
     * @param dto
     * @return
     */
    int totalPlayerGameLogByQry(PlayerLogDto dto);
}