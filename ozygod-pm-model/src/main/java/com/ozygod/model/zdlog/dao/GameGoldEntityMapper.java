package com.ozygod.model.zdlog.dao;

import com.ozygod.model.zdconfig.vo.PlayersWinLoseVO;
import com.ozygod.model.zdlog.bo.GameWinningDetailBO;
import com.ozygod.model.zdlog.bo.PlayerGameLogBO;
import com.ozygod.model.zdlog.dto.BankGoldDto;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdlog.entity.GameGoldEntity;

import java.util.List;

public interface GameGoldEntityMapper {
    int insert(GameGoldEntity record);

    int insertSelective(GameGoldEntity record);

    /**
     * 查询玩家游戏日志列表
     * @param dto
     * @return
     */
    List<PlayerGameLogBO> listPlayerGameLogByQry(PlayerLogDto dto);

    /**
     * 查询玩家游戏日志列表
     * 针对旧有业务
     * @param dto
     * @return
     */
    List<PlayerGameLogBO> listPlayerGameLog1ByQry(PlayerLogDto dto);

    /**
     * 查询玩家游戏日志列表总数
     * @param dto
     * @return
     */
    int totalPlayerGameLogByQry(PlayerLogDto dto);

    /**
     * 查询游戏输赢明细
     * @param dto
     * @return
     */
    List<GameWinningDetailBO> listGameWinningDetailByQry(PlayerLogDto dto);

    /**
     * 查询玩家输赢明细
     * @param dto
     * @return
     */
    List<GameWinningDetailBO> listUserGameDetailByQry(PlayerLogDto dto);

    /**
     * 查询玩家输赢明细总数
     * @param dto
     * @return
     */
    int totalUserGameDetailByQry(PlayerLogDto dto);

    /**
     * 根据时间段统计返佣
     * @param dto
     * @return
     */
    long countSpreadMoneyByQry(PlayerLogDto dto);

    /**
     * 根据时间段统计赠送金币
     * @param dto
     * @return
     */
    long countGiftGoldByQry(PlayerLogDto dto);

    /**
     * 玩家输赢
     * @param dto
     * @return
     */
    PlayersWinLoseVO playersWinLose(PlayerLogDto dto);
}
