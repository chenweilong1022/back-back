package com.ozygod.platform.service;

import com.ozygod.model.common.bo.FinancialStatementBO;
import com.ozygod.model.zdgame.bo.ComplexRechargeCountBO;
import com.ozygod.model.zdgame.bo.NoticeBO;
import com.ozygod.model.zdgame.bo.PlatformOverviewBO;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdgame.entity.AppChannelTypeEntity;
import com.ozygod.model.zdlog.bo.*;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdmanage.bo.ShareImageBO;
import com.ozygod.model.zdmanage.bo.SysConfigsBO;
import com.ozygod.model.zdmanage.bo.SysDomainBO;
import com.ozygod.model.zdmanage.bo.VipChannelBO;
import com.ozygod.model.zdmanage.dto.ConfigDto;
import com.ozygod.model.zdmanage.entity.SysConfigsEntity;

import java.util.List;
import java.util.Map;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/19
 */
public interface IPlatformService {
    /**
     * 查询平台概况
     * @return
     */
    PlatformOverviewBO getPlatformOverview();

    /**
     * 玩家日充值统计汇总
     * @param dto
     * @return
     */
    ComplexRechargeCountBO getComplexRechargeCountByQry(PlatformDto dto);

    /**
     * 近几日分渠道充值统计
     * @param dto
     * @return
     */
    List<Map<String, Object>> listRecentDaysRechargeCountByQry(PlatformDto dto);

    /**
     * 查询按日统计玩家人数列表
     * @param dto
     * @return
     */
    List<DailyPlayersCountBO> listDailyPlayersCountByQry(PlatformDto dto);

    /**
     * 查询按日统计玩家人数列表总数
     * @param dto
     * @return
     */
    int totalDailyPlayersCountByQry(PlatformDto dto);

    /**
     * 查询按日统计财富列表
     * @param dto
     * @return
     */
    List<DailyGoldCountBO> listDailyGoldCountByQry(PlatformDto dto);

    /**
     * 查询按日统计财富列表总数
     * @param dto
     * @return
     */
    int totalDailyGoldCountByQry(PlatformDto dto);

    /**
     * 查询间隔财富统计总和
     * @param dto
     * @return
     */
    DailyGoldCountBO getDailyGoldCountSumByQry(PlatformDto dto);

    /**
     * 查询指定游戏每日税收统计列表
     * @param dto
     * @return
     */
    List<DailyGameTaxGoldCountBO> listDailyGameTaxGoldCountByQry(PlatformDto dto);

    /**
     * 查询指定游戏每日税收统计列表总数
     * @param dto
     * @return
     */
    int totalDailyGameTaxGoldCountByQry(PlatformDto dto);

    /**
     * 注册留存率每日统计列表
     * @param dto
     * @return
     */
    List<DayRegAliveCountBO> listDailyRegAliveCountByQry(PlatformDto dto);

    /**
     * 注册留存率每日统计列表总数
     * @param dto
     * @return
     */
    int totalDailyRegAliveCountByQry(PlatformDto dto);

    /**
     * 查询渠道类型
     * @return
     */
    List<AppChannelTypeEntity> listAppChannelType();

    /**
     * 查询公告列表
     * @param dto
     * @return
     */
    List<NoticeBO> listNoticeByQry(PlatformDto dto);

    /**
     * 查询公告列表总数
     * @param dto
     * @return
     */
    int totalNoticeByQry(PlatformDto dto);

    /**
     * 发布公告
     * @param noticeId
     * @param managerId
     * @return
     */
    int publishNotice(Integer noticeId, Integer managerId);

    /**
     * 下架公告
     * @param noticeId
     * @param managerId
     * @return
     */
    int unpublishNotice(Integer noticeId, Integer managerId);

    /**
     * 删除公告
     * @param noticeId
     * @return
     */
    int deleteNotice(Integer noticeId);

    /**
     * 创建报告
     * @param bo
     * @return
     */
    int createNotice(NoticeBO bo);

    /**
     * 新增VIP充值通道
     * @param bo
     * @return
     */
    int saveVipChannel(VipChannelBO bo);

    /**
     * 查询VIP充值通道列表
     * @param dto
     * @return
     */
    List<VipChannelBO> listVipChannelByQry(PlatformDto dto);

    /**
     * 查询VIP充值通道总数
     * @param dto
     * @return
     */
    int totalVipChannelByQry(PlatformDto dto);

    /**
     * 修改VIP充值通道
     * @param bo
     * @return
     */
    int updateVipChannel(VipChannelBO bo);

    /**
     * 新增系统配置
     * @param entity
     * @return
     */
    int saveSysConfigs(SysConfigsBO entity);

    /**
     * 查询系统配置列表
     * @param dto
     * @return
     */
    List<SysConfigsEntity> listSysConfigsByQry(ConfigDto dto);

    /**
     * 查询系统配置列表总数
     * @param dto
     * @return
     */
    int totalSysConfigsByQry(ConfigDto dto);

    /**
     * 新增系统配置
     * @param entity
     * @return
     */
    int updateSysConfig(SysConfigsBO entity);

    /**
     * 获取系统配置项详情
     * @param code
     * @return
     */
    SysConfigsBO getSysConfigsByQry(String code);

    /**
     * 查询游戏输赢明细
     * @param dto
     * @return
     */
    List<GameWinningDetailBO> listGameWinningDetailByQry(PlayerLogDto dto);

    /**
     * 查询充值对账单
     * @param dto
     * @return
     */
    List<FinancialStatementBO> listRechargeStatementByQry(PlatformDto dto);

    /**
     * 查询提现对账单
     * @param dto
     * @return
     */
    List<FinancialStatementBO> listWithdrawStatementByQry(PlatformDto dto);


    /**
     * 查询域名列表
     * @param dto
     * @return
     */
    List<SysDomainBO> listSysDomainByQry(ConfigDto dto);

    /**
     * 查询域名列表总数
     * @param dto
     * @return
     */
    int totalSysDomainByQry(ConfigDto dto);

    /**
     * 新增域名
     * @param bo
     * @return
     */
    int saveSysDomain(SysDomainBO bo);

    /**
     * 更新域名
     * @param bo
     * @return
     */
    int updateSysDomain(SysDomainBO bo);

    /**
     * 新增分享图
     * @param bo
     * @return
     */
    int saveShareImage(ShareImageBO bo);

    /**
     * 查询分享图列表
     * @param dto
     * @return
     */
    List<ShareImageBO> listShareImageByQry(PlatformDto dto);

    /**
     * 查询分享图总数
     * @param dto
     * @return
     */
    int countShareImageByQry(PlatformDto dto);

    /**
     * 更新分享图
     * @param bo
     * @return
     */
    int updateShareImage(ShareImageBO bo);
}
