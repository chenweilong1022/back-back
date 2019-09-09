package com.ozygod.base.service;

/**
 * @title: 推送数据给游戏的通用Service
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/17
 */
public interface IGamePushService {

    /**
     * 通知游戏规则变更
     * @param gameId
     * @param roomId
     * @param tableId
     * @param rule
     */
    void notifyGameRuleChange(Integer gameId, Integer roomId, Integer tableId, String rule);

    /**
     * 通知游戏黑白名单
     * @param type
     * @param userListStr
     */
    void notifyGameBlackAndWhiteList(Integer type, String userListStr);
}
