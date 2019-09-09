package com.ozygod.game.service.impl;

import com.alibaba.fastjson.JSON;
import com.ozygod.base.service.IGamePushService;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.Constant;
import com.ozygod.game.service.IGameRuleService;
import com.ozygod.game.util.GameConstant;
import com.ozygod.model.zdmanage.bo.GameRuleBO;
import com.ozygod.model.zdmanage.dao.GameRuleEntityMapper;
import com.ozygod.model.zdmanage.dto.GameRuleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/16
 */
@Component
public class GameRuleServiceImpl implements IGameRuleService {

    @Autowired
    private GameRuleEntityMapper gameRuleEntityMapper;

    @Autowired
    private IGamePushService gamePushService;

    /**
     * 保存游戏规则
     *
     * @param bo
     * @return
     */
    @Override
    public int saveGameRule(GameRuleBO bo) {
        int result = 0;
        Map<String, Object> ruleMap = new HashMap<>();

        if (bo.getId() == null || bo.getId() == 0) {
            result = gameRuleEntityMapper.insertSelective(bo);
            ruleMap.put("action", Constant.ACTION_ADD);
        } else {
            result = gameRuleEntityMapper.updateByPrimaryKeySelective(bo);
            ruleMap.put("action", Constant.ACTION_UPDATE);
        }

        if (result > 0) {
            //黑名单
            if (bo.getRuleType() == GameConstant.BLACK_LIST) {
                ruleMap.put("blackListRule", bo.getRule());
            } else if (bo.getRuleType() == GameConstant.WHITE_LIST) {
                ruleMap.put("whiteListRule", bo.getRule());
            }
            String rule = JSON.toJSONString(ruleMap);
            gamePushService.notifyGameRuleChange(bo.getPartyId(),0,0, rule);
        }
        return result;
    }

    /**
     * 查询游戏规则
     *
     * @param dto
     * @return
     */
    @Override
    public GameRuleBO getGameRuleByQry(GameRuleDto dto) {
        return gameRuleEntityMapper.getGameRuleByDto(dto);
    }
}
