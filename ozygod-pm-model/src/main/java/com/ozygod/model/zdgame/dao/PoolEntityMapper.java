package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.entity.PoolEntity;

public interface PoolEntityMapper {
    int insert(PoolEntity record);

    int insertSelective(PoolEntity record);

    /**
     * 根据roomid查询房间库存金币
     * @param roomId
     * @return
     */
    Integer getPoolMoneyByRoomId(Integer roomId);
}