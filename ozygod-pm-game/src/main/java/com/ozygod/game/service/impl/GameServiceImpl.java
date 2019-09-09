package com.ozygod.game.service.impl;

import com.ozygod.game.service.IGameService;
import com.ozygod.model.zdgame.bo.GameBO;
import com.ozygod.model.zdgame.bo.GameRoomBO;
import com.ozygod.model.zdgame.dao.GameEntityMapper;
import com.ozygod.model.zdgame.dao.GameRoomEntityMapper;
import com.ozygod.model.zdgame.dto.GameDto;
import com.ozygod.model.zdgame.entity.GameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/26
 */
@Component
public class GameServiceImpl implements IGameService {
    @Autowired
    private GameEntityMapper gameEntityMapper;
    @Autowired
    private GameRoomEntityMapper gameRoomEntityMapper;
    /**
     * 查询全部游戏
     *
     * @return
     */
    @Override
    public List<GameEntity> listAllGame() {
        return gameEntityMapper.listAllGame();
    }

    /**
     * 查询游戏列表及在线玩家人数
     *
     * @return
     */
    @Override
    public List<GameBO> listGameAndOnlineCount() {
        return gameEntityMapper.listGameAndOnlineCount();
    }

    /**
     * 查询游戏房间列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<GameRoomBO> listGameRoomByQry(GameDto dto) {
        return gameRoomEntityMapper.listGameRoomByQry(dto);
    }
}
