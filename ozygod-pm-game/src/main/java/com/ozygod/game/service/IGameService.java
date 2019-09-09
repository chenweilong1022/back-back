package com.ozygod.game.service;

import com.ozygod.model.zdgame.bo.GameBO;
import com.ozygod.model.zdgame.bo.GameRoomBO;
import com.ozygod.model.zdgame.dto.GameDto;
import com.ozygod.model.zdgame.entity.GameEntity;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/26
 */
public interface IGameService {
    /**
     * 查询全部游戏
     * @return
     */
    List<GameEntity> listAllGame();

    /**
     * 查询游戏列表及在线玩家人数
     * @return
     */
    List<GameBO> listGameAndOnlineCount();

    /**
     * 查询游戏房间列表
     * @param dto
     * @return
     */
    List<GameRoomBO> listGameRoomByQry(GameDto dto);
}
