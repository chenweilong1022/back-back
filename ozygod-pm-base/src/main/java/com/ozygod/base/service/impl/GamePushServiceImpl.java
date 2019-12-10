package com.ozygod.base.service.impl;

import com.ozygod.base.utils.HttpRequestUtil;
import com.ozygod.base.service.IGamePushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/17
 */
@Component
@Slf4j
public class GamePushServiceImpl implements IGamePushService {

    @Value("${game_url}")
    private String gameUrl;

    /**
     * 通知游戏规则变更
     * @param gameId
     * @param roomId
     * @param tableId
     * @param rule
     */
    public void notifyGameRuleChange(Integer gameId, Integer roomId, Integer tableId, String rule) {
        try {
            rule = URLEncoder.encode(rule, "utf-8");

            String result = HttpRequestUtil.sendGet(gameUrl + "/modify_control_rule?gameid="+ gameId
                    + "&roomid=" + roomId + "&tableid=1&rule=" + rule);
            log.info("推送游戏规则状态："+ result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }


    /**
     * 通知游戏黑白名单
     *
     * @param type
     * @param userListStr
     */
    @Override
    public void notifyGameBlackAndWhiteList(Integer type, String userListStr) {
        try {
            log.info("传入参数：" + userListStr);
            userListStr = URLEncoder.encode(userListStr, "utf-8");

            String result = HttpRequestUtil.sendGet(gameUrl + "/game_trick?roomid=0" + (type == 1 ? "&blacklist=":"&viplist=") + userListStr);
            log.info("通知游戏黑白名单状态："+ result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
