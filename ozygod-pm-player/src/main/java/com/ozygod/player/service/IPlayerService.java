package com.ozygod.player.service;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.common.bo.EmailBO;
import com.ozygod.model.zdgame.bo.*;
import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.dto.PlayerOrderDto;
import com.ozygod.model.zdgame.dto.RemitDto;
import com.ozygod.model.zdgame.entity.AccountEntity;
import com.ozygod.model.zdlog.bo.GameWinningDetailBO;
import com.ozygod.model.zdlog.bo.RemitDiamondRecordBO;
import com.ozygod.model.zdlog.bo.RemitGoldRecordBO;
import com.ozygod.model.zdlog.dto.BankGoldDto;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdlog.entity.FreezeUserEntity;
import com.ozygod.model.zdmanage.bo.WithdrawOrderBO;
import com.ozygod.model.zdmanage.dto.BusinessDto;
import com.ozygod.model.zdspread.bo.SpreadUserBO;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/14
 */
public interface IPlayerService {

    /**
     * 根据玩家id获取玩家信息
     * @param playerId
     * @return
     */
    AccountEntity getPlayerById(Integer playerId);

    /**
     * 查询玩家详情
     * @param playerId
     * @return
     */
    PlayerAccountBO getPlayerAccountById(Integer playerId);

    /**
     * 查询玩家详情
     * @param showId
     * @return
     */
    PlayerAccountBO getPlayerAccountByShowId(Integer showId);

    /**
     * 查询玩家帐号列表
     * @param dto
     * @return
     */
    List<PlayerAccountBO> listPlayerAccountByQry(PlayerAccountDto dto);

    /**
     * 查询玩家帐号总数
     * @param dto
     * @return
     */
    int totalCountPlayerAccountByQry(PlayerAccountDto dto);

    /**
     * 给玩家赠送礼金
     * @param bo
     * @return
     */
    int giftMoney(PlayerAccountBO bo);

    /**
     * 设置玩家代理保底
     * @param bo
     * @return
     */
    int setSaleRate(PlayerAccountBO bo);

    /**
     * 设置上级代理
     * @param bo
     * @return
     */
    int setUpSpread(PlayerAccountBO bo);

    /**
     * 发送邮件
     * @param bo
     * @return
     */
    int sendEmail(EmailBO bo);

    /**
     * 查询在线玩家列表
     * @param dto
     * @return
     */
    List<PlayerAccountBO> listOnlinePlayerQry(PlayerAccountDto dto);

    /**
     * 查询在线玩家列表总数
     * @param dto
     * @return
     */
    int totalOnlinePlayerByQry(PlayerAccountDto dto);

    /**
     * 查询未结算玩家列表
     * @param dto
     * @return
     */
    List<PlayerAccountBO> listDebtPlayerQry(PlayerAccountDto dto);

    /**
     * 调整玩家幸运值
     * @param bo
     * @return
     */
    int updatePlayerLuckyRatio(PlayerAccountBO bo);

    /**
     * 查询未结算玩家列表总数
     * @param dto
     * @return
     */
    int totalDebtPlayerByQry(PlayerAccountDto dto);

    /**
     * 查询玩家银行日志列表
     * @param dto
     * @return
     */
    ResponseBO listPlayerBankLogByQry(BankGoldDto dto);

    /**
     * 查询玩家订单列表
     * @param dto
     * @return
     */
    ResponseBO listPlayerOrderByQry(PlayerOrderDto dto);

    /**
     * 更新玩家订单
     * @param bo
     * @return
     */
    int updateOrder(PlayerOrderBO bo);

    /**
     * 查询玩家游戏日志列表
     * @param dto
     * @return
     */
    ResponseBO listPlayerGameLogByQry(PlayerLogDto dto);

    /**
     * 查询玩家钻石游戏日志列表
     * @param dto
     * @return
     */
    ResponseBO listPlayerDiamondLogByQry(PlayerLogDto dto);

    /**
     * 重置玩家登录密码
     * @param playerId
     * @param managerId
     * @return
     */
    int resetPlayerPassword(Integer playerId, Integer managerId);

    /**
     * 重置银行密码
     * @param playerId
     * @param managerId
     * @return
     */
    int resetPlayerBankPassword(Integer playerId, Integer managerId);

    /**
     * 查询玩家冻结帐号
     * @param playerId
     * @return
     */
    List<FreezeUserEntity> listFreezeUserRecordById(Integer playerId);

    /**
     * 冻结玩家帐号
     * @param bo
     * @return
     */
    int freezePlayer(PlayerAccountBO bo);

    /**
     * 批量冻结玩家帐号
     * @param bo
     * @return
     */
    int batchFreezePlayer(PlayerAccountBO bo);

    /**
     * 解冻玩家帐号
     * @param bo
     * @return
     */
    int unfreezePlayer(PlayerAccountBO bo);

    /**
     * 查询玩家输赢记录
     * @param dto
     * @return
     */
    List<RecordBO> listPlayerGameRecordByQry(PlayerAccountDto dto);

    /**
     * 玩家私人房游戏记录统计
     * @param dto
     * @return
     */
    List<PrivateGameRecordBO> listPrivateGameStatisticsByQry(PlayerAccountDto dto);

    /**
     * 玩家私人房游戏记录统计总数
     * @param dto
     * @return
     */
    int totalPrivateGameStatisticsByQry(PlayerAccountDto dto);

    /**
     * 查询玩家私人房游戏记录列表
     * @param dto
     * @return
     */
    List<PrivateGameRecordBO> listPlayerPrivateGameByQry(PlayerAccountDto dto);

    /**
     * 查询玩家私人房游戏记录总数
     * @param dto
     * @return
     */
    int totalPlayerPrivateGameByQry(PlayerAccountDto dto);

    /**
     * 查询相同IP地址的玩家列表
     * @param playerId
     * @return
     */
    List<PlayerAccountBO> listSameAddressPlayerById(Integer playerId);

    /**
     * 查询玩家财富排行列表
     * @param dto
     * @return
     */
    List<PlayerInfoBO> listPlayerWealthRankByQry(PlayerAccountDto dto);

    /**
     * 查询玩家财富排行列表总数
     * @param dto
     * @return
     */
    int totalPlayerWealthRankByQry(PlayerAccountDto dto);

    /**
     * 给指定玩家划账(金币)
     * @param dto
     * @return
     */
    int remitGold(RemitDto dto);

    /**
     * 给指定玩家划账(钻石)
     * @param dto
     * @return
     */
    int remitDiamond(RemitDto dto);

    /**
     * 查询划账日志列表(金币)
     * @param dto
     * @return
     */
    List<RemitGoldRecordBO> listRemitGoldRecordByQry(PlayerLogDto dto);

    /**
     * 查询划账日志列表总数(金币)
     * @param dto
     * @return
     */
    int totalRemitGoldRecordByQry(PlayerLogDto dto);

    /**
     * 获取划账总额(金币)
     * @param dto
     * @return
     */
    long getTotalRemitGoldByQry(PlayerLogDto dto);

    /**
     * 查询划账日志列表(钻石)
     * @param dto
     * @return
     */
    List<RemitDiamondRecordBO> listRemitDiamondRecordByQry(PlayerLogDto dto);

    /**
     * 查询划账日志列表总数(钻石)
     * @param dto
     * @return
     */
    int totalRemitDiamondRecordByQry(PlayerLogDto dto);

    /**
     * 获取划账总额(钻石)
     * @param dto
     * @return
     */
    int getTotalRemitDiamondByQry(PlayerLogDto dto);

    /**
     * 转换玩家为代理用户
     * @param bo
     * @return
     */
    int transferToSpreadUser(SpreadUserBO bo);

    /**
     * 创建玩家提现订单
     * @param bo
     * @return
     */
    int createWithdrawOrder(WithdrawOrderBO bo);

    /**
     * 查询玩家提现列表
     * @param dto
     * @return
     */
    List<WithdrawOrderBO> listWithdrawOrderByQry(BusinessDto dto);

    /**
     * 查询玩家提现总数
     * @param dto
     * @return
     */
    int totalWithdrawOrderbyQry(BusinessDto dto);

    /**
     * 更新玩家订单
     * @param bo
     * @return
     */
    int updateWithdrawOrder(WithdrawOrderBO bo);

    /**
     * 验证玩家是否充值
     * @param showId
     * @return
     */
    int validateUserRechargeState(Long showId);

    /**
     * 查询玩家输赢明细
     * @param dto
     * @return
     */
    List<GameWinningDetailBO> listUserGameDetailByQry(PlayerLogDto dto);

    /**
     * 查询玩家输赢明细总数
     * @param dto
     * @return
     */
    int totalUserGameDetailByQry(PlayerLogDto dto);
}
