package com.ozygod.model.zdlog.dao;

import com.ozygod.model.zdlog.entity.FreezeUserEntity;

import java.util.List;

public interface FreezeUserEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FreezeUserEntity record);

    int insertSelective(FreezeUserEntity record);

    FreezeUserEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreezeUserEntity record);

    int updateByPrimaryKey(FreezeUserEntity record);

    /**
     * 查询玩家冻结帐号记录列表
     * @param playerId
     * @return
     */
    List<FreezeUserEntity> listFreezeUserRecordById(Integer playerId);
}