package com.ozygod.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.Global;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdlog.entity.TblGameRecordEntity;
import com.ozygod.model.zdlog.entity.TblRecordChannelGeneralizeEntity;
import com.ozygod.model.zdlog.service.TblGameRecordService;
import com.ozygod.model.zdlog.service.TblRecordChannelGeneralizeService;
import com.ozygod.model.zdmanage.entity.TblAgentRealtimeEntity;
import com.ozygod.model.zdmanage.entity.TblAgentRecordEntity;
import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.service.TblAgentRealtimeService;
import com.ozygod.model.zdmanage.service.TblAgentRecordService;
import com.ozygod.model.zdmanage.service.TblWithdrawOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 每日渠道推广统计定时任务
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 13:56
 */
@Configuration
@EnableScheduling
public class TblRecordChannelGeneralizeTask {

    @Autowired
    private TblRecordChannelGeneralizeService tblRecordChannelGeneralizeService;
    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblAgentRealtimeService tblAgentRealtimeService;
    @Autowired
    private TblGameRecordService tblGameRecordService;
    @Autowired
    private TblAgentRecordService tblAgentRecordService;
    @Autowired
    private TblOrderService tblOrderService;
    @Autowired
    private TblWithdrawOrderService tblWithdrawOrderService;

    /**
     * 每小时统计一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
//    @Scheduled(cron = "0 59 0-23 * * ?")
    public void configureTasks() {
        DateTime date = DateUtil.endOfDay(DateUtil.date());
        /**
         * 当日第一秒
         */
        DateTime beginOfDay = DateUtil.beginOfDay(DateUtil.date());
        /**
         * 今日最后一秒
         */
        DateTime endOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(date));

        DateTime lastTime = DateUtil.offsetDay(date, -1);
        /**
         * 昨日第一秒
         */
        DateTime lastBeginOfDay = DateUtil.beginOfDay(lastTime);
        /**
         * 昨日最后一秒
         */
        DateTime lastEndOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(lastTime));
        /**
         * 本周
         */
        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetWeek(new Date(), -1), DateUtil.offsetDay(new Date(), -1), DateField.DAY_OF_WEEK);
        /**
         * 今日注册新用户
         */
        List<TblAccountEntity> tblAccountEntities = tblAccountService.registerList(beginOfDay, endOfDay, null);
        if (CollUtil.isEmpty(tblAccountEntities)) {
            return;
        }
        List<Long> newUserIds = tblAccountEntities.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());
        if (CollUtil.isEmpty(newUserIds)) {
            return;
        }
        /**
         * 今日新代理列表
         */
        List<TblAgentRealtimeEntity> newTblAgentRealtimeEntitys = tblAgentRealtimeService.list(new QueryWrapper<TblAgentRealtimeEntity>().lambda()
                .in(TblAgentRealtimeEntity::getUserId, newUserIds)
        );
        if (CollUtil.isEmpty(newTblAgentRealtimeEntitys)) {
            return;
        }
        /**
         * 今日所有推广人id
         */
        List<Long> agentUserIds = newTblAgentRealtimeEntitys.stream().map(TblAgentRealtimeEntity::getSuperId).distinct().collect(Collectors.toList());

        if (CollUtil.isEmpty(agentUserIds)) {
            return;
        }

        List<TblRecordChannelGeneralizeEntity> tblRecordChannelGeneralizeEntities = new ArrayList<TblRecordChannelGeneralizeEntity>();

        for (Long agentUserId : agentUserIds) {

            TblAccountEntity agentTblAccountEntity = tblAccountService.getById(agentUserId);

            /**
             * 今日该代理下的新代理
             */
            List<TblAgentRealtimeEntity> newUserTblAgentRealtimeEntitys = newTblAgentRealtimeEntitys.stream().filter(tblAgentRealtimeEntity -> agentUserId.equals(tblAgentRealtimeEntity.getSuperId())).collect(Collectors.toList());
            /**
             * 今日该代理下的新代理用户id
             */
            List<Long> newUserIdsByAgent = newUserTblAgentRealtimeEntitys.stream().map(TblAgentRealtimeEntity::getUserId).collect(Collectors.toList());
            /**
             * 今日新用户数量
             */
            int newUser = newUserTblAgentRealtimeEntitys.size();
            /**
             * 今日新代理用户游戏记录
             */
            List<TblGameRecordEntity> list = tblGameRecordService.list(new QueryWrapper<TblGameRecordEntity>().lambda()
                    .ge(TblGameRecordEntity::getTime, beginOfDay)
                    .le(TblGameRecordEntity::getTime, endOfDay)
                    .in(TblGameRecordEntity::getUserid, newUserIdsByAgent)
            );
            /**
             * 今日新用户游戏
             */
            int newUserGame = list.stream().map(TblGameRecordEntity::getUserid).distinct().collect(Collectors.toList()).size();
            /**
             * 新用户游戏比
             */
            String newUserGameRatio = Global.PROPORTION;
            if (newUser > 0) {
                double div = NumberUtil.div(newUserGame, newUser);
                newUserGameRatio = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, div);
            }
            /**
             * 查询该代理下全部用户
             */
            List<TblAgentRecordEntity> tblAgentRecordEntitiesAll = tblAgentRecordService.list(new QueryWrapper<TblAgentRecordEntity>().lambda()
                    .eq(TblAgentRecordEntity::getSuperId, agentUserId)
            );

            /**
             * 该代理下全部用户id
             */
            List<Long> userIdsAllByAgent = tblAgentRecordEntitiesAll.stream().map(TblAgentRecordEntity::getUserId).collect(Collectors.toList());

            int activeUsers = 0;
            /**
             * 该代理下所以活跃用户
             */
            List<TblAccountEntity> activeTblAccountEntityByAgent = tblAccountService.list(new QueryWrapper<TblAccountEntity>().lambda()
                    .in(TblAccountEntity::getUserid, userIdsAllByAgent)
                    .last("AND DATEDIFF(DATE_FORMAT(login_time,'%Y%m%d'),DATE_FORMAT(create_time,'%Y%m%d')) >= 1")
                    .gt(TblAccountEntity::getUserid,Global.REAL_USER_ID)
            );

            if (CollUtil.isNotEmpty(activeTblAccountEntityByAgent)) {
                activeUsers = activeTblAccountEntityByAgent.size();
            }
            /**
             * 新用户充值列表
             */
            List<TblOrderEntity> newUserRechargeOrderList = tblOrderService.rechargeOrderList(beginOfDay, endOfDay, newUserIdsByAgent);
            /**
             * 新用户充值
             */
            int newUserRecharge = tblOrderService.recharge(newUserRechargeOrderList);
            /**
             * 新用户充值人数
             */
            int newUsersRechargeNumber = tblOrderService.rechargeUserCount(newUserRechargeOrderList);
            /**
             * 新用户充值比例
             */
            String newUserRechargeRatio = Global.PROPORTION;
            if (newUser > 0) {
                double div = NumberUtil.div(newUsersRechargeNumber, newUser);
                newUserRechargeRatio = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, div);
            }
            /**
             * 新用户兑换列表
             */
            List<TblWithdrawOrderEntity> newUserConversionOrderEntities = tblWithdrawOrderService.haveWithdrawal(beginOfDay, endOfDay, newUserIdsByAgent);
            /**
             * 新用户兑换
             */
            int newUserConversion = tblWithdrawOrderService.totalBack(newUserConversionOrderEntities);
            /**
             * 新用户兑换人
             */
            int newUserConversionNumber = tblWithdrawOrderService.totalBackNumber(newUserConversionOrderEntities);
            /**
             * 新用户充值差
             */
            int newUserRechargePoor = newUserRecharge - newUserConversion;
            /**
             * 该代理所有用户今日充值列表
             */
            List<TblOrderEntity> todayRechargeOrderList = tblOrderService.rechargeOrderList(beginOfDay, endOfDay, userIdsAllByAgent);
            /**
             * 今日充值
             */
            int todayRecharge = tblOrderService.recharge(todayRechargeOrderList);
            /**
             * 今日充值人数
             */
            int todayRechargeNumber = tblOrderService.rechargeUserCount(todayRechargeOrderList);
            /**
             * 该代理所有用户今日兑换列表
             */
            List<TblWithdrawOrderEntity> todayConversionOrderEntities = tblWithdrawOrderService.haveWithdrawal(beginOfDay, endOfDay, userIdsAllByAgent);
            /**
             * 今日兑换
             */
            int todayConversion = tblWithdrawOrderService.totalBack(todayConversionOrderEntities);
            /**
             * 今日兑换人数
             */
            int todayConversionNumber = tblWithdrawOrderService.totalBackNumber(todayConversionOrderEntities);
            /**
             * 今日充值兑换比例
             */
            String todayRechargeConversionRatio = Global.PROPORTION;
            if (todayRecharge > 0) {
                double div = NumberUtil.div(todayConversion, todayRecharge);
                todayRechargeConversionRatio = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, div);
            }
            /**
             * 今日充值兑换人数比例
             */
            String todayRechargeConversionRatioNumber = Global.PROPORTION;
            if (todayRechargeNumber > 0) {
                double div = NumberUtil.div(todayConversionNumber, todayRechargeNumber);
                todayRechargeConversionRatioNumber = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, div);
            }
            /**
             * 充值差
             */
            int rechargePoor = todayRecharge - todayConversion;


            TblRecordChannelGeneralizeEntity tblRecordChannelGeneralizeEntity = new TblRecordChannelGeneralizeEntity();
            tblRecordChannelGeneralizeEntity.setChannel(ObjectUtil.isNull(agentTblAccountEntity)? "3521" :agentTblAccountEntity.getAppChannel() );
            tblRecordChannelGeneralizeEntity.setCurrentDates(endOfDay);
            tblRecordChannelGeneralizeEntity.setAgentUserId(agentUserId);
            tblRecordChannelGeneralizeEntity.setNewUser(newUserTblAgentRealtimeEntitys.size());
//            tblRecordChannelGeneralizeEntity.setNewEquipment();
            tblRecordChannelGeneralizeEntity.setNewUserGame(newUserGame);
//            tblRecordChannelGeneralizeEntity.setNewEquipmentGame();
            tblRecordChannelGeneralizeEntity.setNewUserGameRatio(newUserGameRatio);
//            tblRecordChannelGeneralizeEntity.setNewEquipmentGameRatio();
            tblRecordChannelGeneralizeEntity.setActiveUsers(activeUsers);
//            tblRecordChannelGeneralizeEntity.setActiveEquipment();
            tblRecordChannelGeneralizeEntity.setNewUserRecharge(newUserRecharge);
            tblRecordChannelGeneralizeEntity.setNewUsersRechargeNumber(newUsersRechargeNumber);
            tblRecordChannelGeneralizeEntity.setNewUserRechargeRatio(newUserRechargeRatio);
//            tblRecordChannelGeneralizeEntity.setNewUserEquipmentRatio();
            tblRecordChannelGeneralizeEntity.setNewUserConversion(newUserConversion);
            tblRecordChannelGeneralizeEntity.setNewUserConversionNumber(newUserConversionNumber);
            tblRecordChannelGeneralizeEntity.setNewUserRechargePoor(newUserRechargePoor);
            tblRecordChannelGeneralizeEntity.setTodayRecharge(todayRecharge);
            tblRecordChannelGeneralizeEntity.setTodayRechargeNumber(todayRechargeNumber);
            tblRecordChannelGeneralizeEntity.setTodayConversion(todayConversion);
            tblRecordChannelGeneralizeEntity.setTodayConversionNumber(todayConversionNumber);
            tblRecordChannelGeneralizeEntity.setTodayRechargeConversionRatio(todayRechargeConversionRatio);
            tblRecordChannelGeneralizeEntity.setTodayRechargeConversionRatioNumber(todayRechargeConversionRatioNumber);
            tblRecordChannelGeneralizeEntity.setRechargePoor(rechargePoor);

            tblRecordChannelGeneralizeEntities.add(tblRecordChannelGeneralizeEntity);
        }


        for (TblRecordChannelGeneralizeEntity tblRecordChannelGeneralizeEntity : tblRecordChannelGeneralizeEntities) {
            TblRecordChannelGeneralizeEntity one = tblRecordChannelGeneralizeService.getOne(new QueryWrapper<TblRecordChannelGeneralizeEntity>().lambda()
                    .eq(TblRecordChannelGeneralizeEntity::getCurrentDates, endOfDay)
                    .eq(TblRecordChannelGeneralizeEntity::getAgentUserId, tblRecordChannelGeneralizeEntity.getAgentUserId())
            );

            if (ObjectUtil.isNotNull(one)) {
                tblRecordChannelGeneralizeEntity.setId(one.getId());
                tblRecordChannelGeneralizeService.updateById(tblRecordChannelGeneralizeEntity);
            }else {
                tblRecordChannelGeneralizeService.save(tblRecordChannelGeneralizeEntity);
            }
        }




    }

}
