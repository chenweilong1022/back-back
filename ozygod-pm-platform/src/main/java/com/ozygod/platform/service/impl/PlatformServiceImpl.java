package com.ozygod.platform.service.impl;

import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.Constant;
import com.ozygod.base.utils.HttpRequestUtil;
import com.ozygod.model.common.bo.FinancialStatementBO;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdgame.bo.*;
import com.ozygod.model.zdgame.dao.AppChannelTypeEntityMapper;
import com.ozygod.model.zdgame.dao.GameProcedureMapper;
import com.ozygod.model.zdgame.dao.NoticeEntityMapper;
import com.ozygod.model.zdgame.dao.OrderEntityMapper;
import com.ozygod.model.zdgame.dto.PlayerOrderDto;
import com.ozygod.model.zdgame.entity.AppChannelTypeEntity;
import com.ozygod.model.zdlog.bo.*;
import com.ozygod.model.zdlog.dao.*;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdmanage.bo.*;
import com.ozygod.model.zdmanage.dao.*;
import com.ozygod.model.zdmanage.dto.BusinessDto;
import com.ozygod.model.zdmanage.dto.ConfigDto;
import com.ozygod.model.zdmanage.entity.ShareImageEntity;
import com.ozygod.model.zdmanage.entity.SysConfigsEntity;
import com.ozygod.model.zdmanage.entity.SysDomainEntity;
import com.ozygod.model.zdmanage.entity.VipChannelEntity;
import com.ozygod.platform.service.IPlatformService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/19
 */
@Slf4j
@Component
public class PlatformServiceImpl implements IPlatformService {
    @Autowired
    private GameProcedureMapper gameProcedureMapper;
    @Autowired
    private RecordPlatformDayPlayerEntityMapper platformDayPlayerEntityMapper;
    @Autowired
    private RecordPlatformDayGoldEntityMapper platformDayGoldEntityMapper;
    @Autowired
    private RecordPlatformDayTaxGoldEntityMapper platformDayTaxGoldEntityMapper;
    @Autowired
    private RecordDayRegAliveEntityMapper dayRegAliveEntityMapper;
    @Autowired
    private AppChannelTypeEntityMapper appChannelTypeEntityMapper;
    @Autowired
    private NoticeEntityMapper noticeEntityMapper;
    @Autowired
    private VipChannelEntityMapper vipChannelEntityMapper;
    @Autowired
    private BankGoldEntityMapper bankGoldEntityMapper;
    @Autowired
    private SysConfigsEntityMapper sysConfigsEntityMapper;
    @Autowired
    private GameGoldEntityMapper gameGoldEntityMapper;
    @Autowired
    private OrderEntityMapper orderEntityMapper;
    @Autowired
    private ManagerRemitGoldRecordEntityMapper managerRemitGoldRecordEntityMapper;
    @Autowired
    private WithdrawOrderEntityMapper withdrawOrderEntityMapper;
    @Autowired
    private SysDomainEntityMapper sysDomainEntityMapper;
    @Autowired
    private ShareImageEntityMapper shareImageEntityMapper;

    @Value("${game_url}")
    private String gameUrl;

    /**
     * 查询平台概况
     *
     * @return
     */
    @Override
    public PlatformOverviewBO getPlatformOverview() {
        return gameProcedureMapper.getPlatformOverview();
    }

    /**
     * 玩家日充值统计汇总
     *
     * @param dto
     * @return
     */
    @Override
    public ComplexRechargeCountBO getComplexRechargeCountByQry(PlatformDto dto) {
        ComplexRechargeCountBO complexRechargeCountBO = new ComplexRechargeCountBO();
        List<List<?>> resultList = gameProcedureMapper.listDailyRechargeCountByQry(dto);
        for (List result : resultList) {
            if (result.size() == 1) {
                complexRechargeCountBO.setRechargeCountBO((RechargeCountBO) result.get(0));
            } else {
                complexRechargeCountBO.setDailyRechargeCountList((List<DailyRechargeCountBO>) result);
            }
        }
        return complexRechargeCountBO;
    }

    /**
     * 近几日分渠道充值统计
     *
     * @param dto
     * @return
     */
    @Override
    public List<Map<String, Object>> listRecentDaysRechargeCountByQry(PlatformDto dto) {
        // 查询出人工充值管理列表
        PlayerLogDto managerDto = new PlayerLogDto();
        List<ManagerBO> managerBOS = managerRemitGoldRecordEntityMapper.listRechargeManagerByQry(managerDto);

        // 最终结果列表
        List<Map<String, Object>> resultList = new ArrayList<>();

        // 获取间隔日期的金币变化统计
        List<String> dateList = CommonUtil.getBetweenDates(dto.getStartDate().getTime(), dto.getEndDate().getTime(), "yyyy-MM-dd", 1);

        SimpleDateFormat fullDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < dateList.size(); i++) {
            Map<String, Object> channelMap = new LinkedHashMap<>();

            String currentDateStr = dateList.get(i);
            channelMap.put("recordDate", currentDateStr);

            try {
                Date beginTime = fullDF.parse(currentDateStr + " 00:00:00");
                Date endTime = fullDF.parse(currentDateStr + " 23:59:59");
                // 人工充值统计
                int index = 1;
                for (ManagerBO managerBO : managerBOS) {
                    String key =  "人工充值(" + managerBO.getLoginName() + ")";
                    channelMap.put("channelName" + index, key);

                    PlayerLogDto logDto = new PlayerLogDto();
                    logDto.setManagerId((long)managerBO.getManagerId());
                    logDto.setStartTime(beginTime);
                    logDto.setEndTime(endTime);
                    channelMap.put("channelCount" + index, Math.abs(managerRemitGoldRecordEntityMapper.countRechargeGoldByQry(logDto)));

                    index ++;
                }

                // 自动充值统计
                PlayerOrderDto orderDto = new PlayerOrderDto();
                orderDto.setStartTime(beginTime);
                orderDto.setEndTime(endTime);
                channelMap.put("recharge", orderEntityMapper.totalRechargeAmountByQry(orderDto));

                // 提现统计
                BusinessDto businessDto = new BusinessDto();
                businessDto.setStartTime(beginTime);
                businessDto.setEndTime(endTime);
                channelMap.put("withdraw",withdrawOrderEntityMapper.countWithdrawOrderByQry(businessDto));

                // 分享赠送
                PlayerLogDto logDto = new PlayerLogDto();
                logDto.setStartTime(beginTime);
                logDto.setEndTime(endTime);
                logDto.setReason(Constant.GAME_GOLD_CHANGE_SHARE);
                channelMap.put("share", gameGoldEntityMapper.countGiftGoldByQry(logDto));

                // 礼金
                logDto.setReason(Constant.GAME_GOLD_CHANGE_GIFT);
                channelMap.put("gift", gameGoldEntityMapper.countGiftGoldByQry(logDto));
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }

            resultList.add(channelMap);
        }

        return resultList;
    }

    /**
     * 查询按日统计玩家人数列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<DailyPlayersCountBO> listDailyPlayersCountByQry(PlatformDto dto) {
        return platformDayPlayerEntityMapper.listDailyPlayersCountByQry(dto);
    }

    /**
     * 查询按日统计玩家人数列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalDailyPlayersCountByQry(PlatformDto dto) {
        return platformDayPlayerEntityMapper.totalDailyPlayersCountByQry(dto);
    }

    /**
     * 查询按日统计财富列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<DailyGoldCountBO> listDailyGoldCountByQry(PlatformDto dto) {
        return platformDayGoldEntityMapper.listDailyGoldCountByQry(dto);
    }

    /**
     * 查询按日统计财富列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalDailyGoldCountByQry(PlatformDto dto) {
        return platformDayGoldEntityMapper.totalDailyGoldCountByQry(dto);
    }

    /**
     * 查询间隔财富统计总和
     *
     * @param dto
     * @return
     */
    @Override
    public DailyGoldCountBO getDailyGoldCountSumByQry(PlatformDto dto) {
        return platformDayGoldEntityMapper.getDailyGoldCountSumByQry(dto);
    }

    /**
     * 查询指定游戏每日税收统计列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<DailyGameTaxGoldCountBO> listDailyGameTaxGoldCountByQry(PlatformDto dto) {
        return platformDayTaxGoldEntityMapper.listDailyGameTaxGoldCountByQry(dto);
    }

    /**
     * 查询指定游戏每日税收统计列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalDailyGameTaxGoldCountByQry(PlatformDto dto) {
        return platformDayTaxGoldEntityMapper.totalDailyGameTaxGoldCountByQry(dto);
    }

    /**
     * 注册留存率每日统计列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<DayRegAliveCountBO> listDailyRegAliveCountByQry(PlatformDto dto) {
        return dayRegAliveEntityMapper.listDailyRegAliveCountByQry(dto);
    }

    /**
     * 注册留存率每日统计列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalDailyRegAliveCountByQry(PlatformDto dto) {
        return dayRegAliveEntityMapper.totalDailyRegAliveCountByQry(dto);
    }

    /**
     * 查询渠道类型
     *
     * @return
     */
    @Override
    public List<AppChannelTypeEntity> listAppChannelType() {
        return appChannelTypeEntityMapper.listAppChannelType();
    }

    /**
     * 查询公告列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<NoticeBO> listNoticeByQry(PlatformDto dto) {
        return noticeEntityMapper.listNoticeByQry(dto);
    }

    /**
     * 查询公告列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalNoticeByQry(PlatformDto dto) {
        return noticeEntityMapper.totalNoticeByQry(dto);
    }

    /**
     * 发布公告
     *
     * @param noticeId
     * @param managerId
     * @return
     */
    @Override
    public int publishNotice(Integer noticeId, Integer managerId) {
        if (noticeEntityMapper.existNoticeById(noticeId) >= 5) {
            return 2;
        }

        String result = HttpRequestUtil.sendGet(gameUrl + "/publish_notice?notice_id=" + noticeId + "&manager_id=" + managerId);
        log.info("调用结果：" + result);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 下架公告
     *
     * @param noticeId
     * @param managerId
     * @return
     */
    @Override
    public int unpublishNotice(Integer noticeId, Integer managerId) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/unpublish_notice?notice_id=" + noticeId + "&manager_id=" + managerId);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 删除公告
     *
     * @param noticeId
     * @return
     */
    @Override
    public int deleteNotice(Integer noticeId) {
        return noticeEntityMapper.deleteByPrimaryKey(noticeId);
    }

    /**
     * 创建报告
     *
     * @param bo
     * @return
     */
    @Override
    public int createNotice(NoticeBO bo) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/new_notice?app_channel=" + bo.getChannel() + "&manager_id=" + bo.getManagerId()
        + "&game_id=0&content=" + bo.getContent() + "&note_type=" + bo.getNoticeType());
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 新增VIP充值通道
     *
     * @param bo
     * @return
     */
    @Override
    public int saveVipChannel(VipChannelBO bo) {
        VipChannelEntity entity = new VipChannelEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return vipChannelEntityMapper.insertSelective(entity);
    }

    /**
     * 查询VIP充值通道列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<VipChannelBO> listVipChannelByQry(PlatformDto dto) {
        return vipChannelEntityMapper.listVipChannelByQry(dto);
    }

    /**
     * 查询VIP充值通道总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalVipChannelByQry(PlatformDto dto) {
        return vipChannelEntityMapper.totalVipChannelByQry(dto);
    }

    /**
     * 修改VIP充值通道
     *
     * @param bo
     * @return
     */
    @Override
    public int updateVipChannel(VipChannelBO bo) {
        VipChannelEntity entity = new VipChannelEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return vipChannelEntityMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 新增系统配置
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional("zdmanageTransactionManager")
    public int saveSysConfigs(SysConfigsBO bo) {
        SysConfigsEntity entity = new SysConfigsEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysConfigsEntityMapper.insertSelective(entity);
    }

    /**
     * 新增系统配置
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional("zdmanageTransactionManager")
    public int updateSysConfig(SysConfigsBO bo) {
        SysConfigsEntity entity = new SysConfigsEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysConfigsEntityMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 获取系统配置项详情
     *
     * @param code
     * @return
     */
    @Override
    public SysConfigsBO getSysConfigsByQry(String code) {
        return sysConfigsEntityMapper.getSysConfigsByQry(code);
    }

    /**
     * 查询系统配置列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<SysConfigsEntity> listSysConfigsByQry(ConfigDto dto) {
        return sysConfigsEntityMapper.listSysConfigsByQry(dto);
    }

    /**
     * 查询系统配置列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalSysConfigsByQry(ConfigDto dto) {
        return sysConfigsEntityMapper.totalSysConfigsByQry(dto);
    }

    /**
     * 查询游戏输赢明细
     *
     * @param dto
     * @return
     */
    @Override
    public List<GameWinningDetailBO> listGameWinningDetailByQry(PlayerLogDto dto) {
        return gameGoldEntityMapper.listGameWinningDetailByQry(dto);
    }

    /**
     * 查询充值对账单
     *
     * @param dto
     * @return
     */
    @Override
    public List<FinancialStatementBO> listRechargeStatementByQry(PlatformDto dto) {
        List<FinancialStatementBO> resultList = new ArrayList<>();
        // 第三方支付
        FinancialStatementBO thirdPay = new FinancialStatementBO();
        thirdPay.setTypeName("第三方支付");
        PlayerOrderDto orderDto = new PlayerOrderDto();
        orderDto.setStartTime(dto.getStartTime());
        orderDto.setEndTime(dto.getEndTime());
        thirdPay.setAmount(orderEntityMapper.totalRechargeAmountByQry(orderDto));
        thirdPay.setTax(0L);
        thirdPay.setActualAmount(orderEntityMapper.totalRechargeAmountByQry(orderDto));
        resultList.add(thirdPay);
        // 人工充值
        PlayerLogDto logDto = new PlayerLogDto();
        logDto.setStartTime(dto.getStartTime());
        logDto.setEndTime(dto.getEndTime());
        List<FinancialStatementBO> manualList = managerRemitGoldRecordEntityMapper.listManualRechargeStatementByQry(logDto);
        resultList.addAll(manualList);
        return resultList;
    }

    /**
     * 查询提现对账单
     *
     * @param dto
     * @return
     */
    @Override
    public List<FinancialStatementBO> listWithdrawStatementByQry(PlatformDto dto) {
        return withdrawOrderEntityMapper.listWithdrawStatementByQry(dto);
    }

    /**
     * 查询域名列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<SysDomainBO> listSysDomainByQry(ConfigDto dto) {
        return sysDomainEntityMapper.listSysDomainByQry(dto);
    }

    /**
     * 查询域名列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalSysDomainByQry(ConfigDto dto) {
        return sysDomainEntityMapper.totalSysDomainByQry(dto);
    }

    /**
     * 新增域名
     *
     * @param bo
     * @return
     */
    @Override
    public int saveSysDomain(SysDomainBO bo) {
        SysDomainEntity entity = new SysDomainEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return sysDomainEntityMapper.insertSelective(entity);
    }

    /**
     * 更新域名
     *
     * @param bo
     * @return
     */
    @Override
    public int updateSysDomain(SysDomainBO bo) {
        SysDomainEntity entity = new SysDomainEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return sysDomainEntityMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 新增分享图
     *
     * @param bo
     * @return
     */
    @Override
    public int saveShareImage(ShareImageBO bo) {
        ShareImageEntity entity = new ShareImageEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return shareImageEntityMapper.insertSelective(entity);
    }

    /**
     * 查询分享图列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<ShareImageBO> listShareImageByQry(PlatformDto dto) {
        return shareImageEntityMapper.listShareImageByQry(dto);
    }

    /**
     * 查询分享图总数
     *
     * @param dto
     * @return
     */
    @Override
    public int countShareImageByQry(PlatformDto dto) {
        return shareImageEntityMapper.countShareImageByQry(dto);
    }

    /**
     * 更新分享图
     *
     * @param bo
     * @return
     */
    @Override
    public int updateShareImage(ShareImageBO bo) {
        ShareImageEntity entity = new ShareImageEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return shareImageEntityMapper.updateByPrimaryKeySelective(entity);
    }
}
