package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.GameRoomBO;
import com.ozygod.model.zdgame.dto.GameDto;
import com.ozygod.model.zdgame.entity.GameRoomEntity;

import java.util.List;

public interface GameRoomEntityMapper {
    int deleteByPrimaryKey(Integer roomid);

    int insert(GameRoomEntity record);

    int insertSelective(GameRoomEntity record);

    GameRoomEntity selectByPrimaryKey(Integer roomid);

    int updateByPrimaryKeySelective(GameRoomEntity record);

    int updateByPrimaryKey(GameRoomEntity record);

    /**
     * 查询游戏房间列表
     * @param dto
     * @return
     */
    List<GameRoomBO> listGameRoomByQry(GameDto dto);
}