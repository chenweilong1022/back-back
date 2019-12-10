package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.bo.GameRuleBO;
import com.ozygod.model.zdmanage.dto.GameRuleDto;
import com.ozygod.model.zdmanage.entity.GameRuleEntity;

public interface GameRuleEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameRuleEntity record);

    int insertSelective(GameRuleEntity record);

    GameRuleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameRuleEntity record);

    int updateByPrimaryKey(GameRuleEntity record);

    /**
     * 查询指定规则
     * @param dto
     * @return
     */
    GameRuleBO getGameRuleByDto(GameRuleDto dto);
}