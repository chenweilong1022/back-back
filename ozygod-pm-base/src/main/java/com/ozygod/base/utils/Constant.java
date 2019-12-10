package com.ozygod.base.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @date: Created in 17:51 2018/8/30 0030.
 */
public class Constant {
    /**
     * 手机登录标识
     */
    public static final String APP_LOGIN_SUFFIX = "_APP";

    /**
     * API版本——v1
     */
    public static final String API_VERSION_V1 = "api-version=v1";
    /**
     * API版本--v2
     */
    public static final String API_VERSION_V2 = "api-version=v2";

    public static final List<String> SUCCESS_FLAG_LIST = Arrays.asList("success","ok");

    /**
     * 通用状态，true
     */
    public static final Integer YES = 1;

    /**
     * 新增
     */
    public static final Integer ACTION_ADD = 1;
    /**
     * 更新
     */
    public static final Integer ACTION_UPDATE = 2;
    /**
     * 删除
     */
    public static final Integer ACTION_DELETED = 3;


    /**
     * 提现状态：未处理
     */
    public static final int WITHDRAW_STATE_DEFAULT = 0;
    /**
     * 提现状态：审核
     */
    public static final int WITHDRAW_STATE_APPROVAL = 1;
    /**
     * 提现状态：完成
     */
    public static final int WITHDRAW_STATE_COMPLETE = 2;
    /**
     * 提现状态：取消
     */
    public static final int WITHDRAW_STATE_CANCEL = 3;

    /**
     * 系统配置项--提现税率
     */
    public static final String SYS_CONFIGS_WITHDRAW_RATIO = "withdrawRatio";
    /**
     * 系统配置--钉钉群配置
     */
    public static final String SYS_CONFIGS_DING_TALK_GROUP = "dingTalkGroup";
    /**
     * 系统配置--礼金设置
     */
    public static final String SYS_CONFIGS_GIFT_AMOUNT = "giftAmount";

    /**
     * 游戏金币变动原因--充值
     */
    public static final Integer GAME_GOLD_CHANGE_RECHARGE = 1;
    /**
     * 游戏金币变动原因--礼金
     */
    public static final Integer GAME_GOLD_CHANGE_GIFT = 3;
    /**
     * 游戏金币变动原因--分享赠送
     */
    public static final Integer GAME_GOLD_CHANGE_SHARE = 6;

}
