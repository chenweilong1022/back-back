package com.ozygod.model.zdlog.dao;

import com.ozygod.model.zdgame.bo.PlayerAccountBO;
import com.ozygod.model.zdlog.dto.PlayerLogDto;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/20
 */
public interface LogProcedureMapper {
    /**
     * 查询平台人数统计
     * @param dto
     * @return
     */
//    List<List<?>> listDailyRechargeCountByQry(PlatformDto dto);

    /**
     * 查询玩家游戏日志
     * @param dto
     * @return
     */
    List<List<?>> listPlayerGameLogByQry(PlayerLogDto dto);

    /**
     * 查询相同IP地址的玩家列表
     * @param playerId
     * @return
     */
    List<PlayerAccountBO> listSameAddressPlayerById(Integer playerId);
}
