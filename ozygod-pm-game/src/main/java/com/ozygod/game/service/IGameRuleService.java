package com.ozygod.game.service;

import com.ozygod.model.zdmanage.bo.GameRuleBO;
import com.ozygod.model.zdmanage.dto.GameRuleDto;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/16
 */
public interface IGameRuleService {

    /**
     * 查询游戏规则
     * @param dto
     * @return
     */
    GameRuleBO getGameRuleByQry(GameRuleDto dto);

    /**
     * 保存游戏规则
     * @param bo
     * @return
     */
    int saveGameRule(GameRuleBO bo);
}
