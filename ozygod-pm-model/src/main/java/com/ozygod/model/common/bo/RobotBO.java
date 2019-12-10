package com.ozygod.model.common.bo;

import com.ozygod.base.bo.BaseBO;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/11
 */
@Data
public class RobotBO extends BaseBO {
    /**
     * 连续n秒内⽆无⼈人抢
     */
    private Integer maxWaitTime;
    /**
     * n秒后退出房间
     */
    private Integer maxStayTime;
    /**
     * 少于n个红包，机器器⼈人发红包
     */
    private Integer minHongbaoCount;
    /**
     * 发红包数额
     */
    private Integer hongbaoMoney;
    /**
     * 红包划分个数
     */
    private Integer hongbaoSize;
    /**
     * 进⼊入房间携带⾦金金币
     */
    private InitMoneyBO initMoney;
    /**
     * 赢⾄至n退出房间
     */
    private Integer maxMoney;
    /**
     * 输⾄至n退出房间
     */
    private Integer minMoney;
    /**
     * 单个房间机器器⼈人数
     */
    private Integer robotCount;
    /**
     * 游戏名称
     */
    private String game;
    /**
     * 房间库存金币
     */
    private Integer roomMoney;

}
