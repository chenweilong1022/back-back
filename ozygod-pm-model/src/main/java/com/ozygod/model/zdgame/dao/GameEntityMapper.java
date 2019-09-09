package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.GameBO;
import com.ozygod.model.zdgame.entity.GameEntity;

import java.util.List;

public interface GameEntityMapper {
    int deleteByPrimaryKey(Integer gameid);

    int insert(GameEntity record);

    int insertSelective(GameEntity record);

    GameEntity selectByPrimaryKey(Integer gameid);

    int updateByPrimaryKeySelective(GameEntity record);

    int updateByPrimaryKey(GameEntity record);

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
}