package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.entity.AppChannelTypeEntity;

import java.util.List;

public interface AppChannelTypeEntityMapper {
    int deleteByPrimaryKey(String channelId);

    int insert(AppChannelTypeEntity record);

    int insertSelective(AppChannelTypeEntity record);

    AppChannelTypeEntity selectByPrimaryKey(String channelId);

    int updateByPrimaryKeySelective(AppChannelTypeEntity record);

    int updateByPrimaryKey(AppChannelTypeEntity record);

    /**
     * 查询渠道类型
     * @return
     */
    List<AppChannelTypeEntity> listAppChannelType();
}