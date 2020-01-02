package com.ozygod.task;
import java.util.Date;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.AccountLoginType;
import com.ozygod.base.enums.AccountRegisterChannel;
import com.ozygod.base.enums.Global;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblPlayerinfoEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdgame.service.TblPlayerinfoService;
import com.ozygod.model.zdlog.entity.TblGameGoldEntity;
import com.ozygod.model.zdlog.entity.TblGameRecordEntity;
import com.ozygod.model.zdlog.entity.TblRecordChannelDailyEntity;
import com.ozygod.model.zdlog.service.TblGameGoldService;
import com.ozygod.model.zdlog.service.TblGameRecordService;
import com.ozygod.model.zdlog.service.TblRecordChannelDailyService;
import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.service.TblWithdrawOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 渠道日报定时任务
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:41
 */
@Configuration
@EnableScheduling
public class RecordTotalGoldEverydayTask {

    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblRecordChannelDailyService tblRecordChannelDailyService;
    @Autowired
    private TblPlayerinfoService tblPlayerinfoService;
    @Autowired
    private TblOrderService tblOrderService;
    @Autowired
    private TblWithdrawOrderService tblWithdrawOrderService;
    @Autowired
    private TblGameGoldService tblGameGoldService;
    @Autowired
    private TblGameRecordService tblGameRecordService;

    /**
     * 每小时统计一次
     */
    @Scheduled(cron = "0 59 0-23 * * ?")
    public void configureTasks() {

        DateTime date = DateUtil.endOfDay(DateUtil.date());
        DateTime beginOfDay = DateUtil.beginOfDay(DateUtil.date());
        /**
         * 今日最后一分钟
         */
        DateTime endOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(date));



        AccountRegisterChannel[] accountRegisterChannels = AccountRegisterChannel.values();
        AccountLoginType[] accountLoginTypes = AccountLoginType.values();


        int count = tblRecordChannelDailyService.count(new QueryWrapper<TblRecordChannelDailyEntity>().lambda()
                .eq(TblRecordChannelDailyEntity::getCurrentDates, endOfDay)
        );




        List<TblRecordChannelDailyEntity> tblRecordChannelDailyEntities = new ArrayList();
        for (AccountRegisterChannel accountRegisterChannel : accountRegisterChannels) {
            //暂时不处理其他渠道
            if(accountRegisterChannel.getKey().equals(AccountRegisterChannel.COTHER.getKey())) {
                continue;
            }
            for (AccountLoginType accountLoginType : accountLoginTypes) {
                /**
                 * 获取当前渠道 当前登录方式的用户
                 */
                List<TblAccountEntity> list = tblAccountService.list(new QueryWrapper<TblAccountEntity>().lambda()
                        .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
                        .eq(TblAccountEntity::getAppChannel, accountRegisterChannel.getValue())
                        .eq(TblAccountEntity::getPlatform, accountLoginType.getValue())
                );
                if (CollUtil.isEmpty(list)) {
                    continue;
                }
                /**
                 * 目前当前渠道所有用户id
                 */
                List<Long> userids = list.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());
                /**
                 * 当前渠道所有用户的信息
                 */
                List<TblPlayerinfoEntity> tblPlayerinfoEntities = tblPlayerinfoService.list(new QueryWrapper<TblPlayerinfoEntity>().lambda()
                        .in(TblPlayerinfoEntity::getUserid, userids)
                );
                /**
                 * 玩家身上总金币
                 */
                long gold = tblPlayerinfoEntities.stream().mapToLong(TblPlayerinfoEntity::getGold).sum();
                /**
                 * 银行总金币
                 */
                long bankGold = tblPlayerinfoEntities.stream().mapToLong(TblPlayerinfoEntity::getBankGold).sum();
                /**
                 * 总金币
                 */
                long totalGold = gold + bankGold;
                /**
                 * 今日充值
                 */
                int recharge = tblOrderService.recharge(beginOfDay, endOfDay, userids);

                /**
                 * 今日提现
                 */
                List<TblWithdrawOrderEntity> tblWithdrawOrderEntitys = tblWithdrawOrderService.haveWithdrawal(beginOfDay, endOfDay,userids);
                /**
                 * 总提现
                 */
                int conversion = tblWithdrawOrderService.totalBack(tblWithdrawOrderEntitys);
                /**
                 * 今日注册用户
                 */
                int registerUsers = tblAccountService.registerNumber(beginOfDay, endOfDay, userids);
                /**
                 * 今日登录用户
                 */
                int loginUsers = tblAccountService.loginNumber(beginOfDay, endOfDay, null, userids);
                /**
                 * 今日营收
                 */
                int profit = recharge - conversion;
                /**
                 * 今日税收用户
                 */
                List<TblGameGoldEntity> tblGameGoldEntities = tblGameGoldService.tblGameGoldEntities(beginOfDay, endOfDay, userids);
                /**
                 * 总税收
                 */
                long totalRevenue = tblGameGoldService.totalRevenue(tblGameGoldEntities);
                /**
                 * 游戏记录
                 */
                int gameRecord = tblGameRecordService.count(new QueryWrapper<TblGameRecordEntity>().lambda()
                        .ge(TblGameRecordEntity::getTime, beginOfDay)
                        .le(TblGameRecordEntity::getTime, endOfDay)
                        .in(TblGameRecordEntity::getUserid, userids)
                );


                TblRecordChannelDailyEntity tblRecordChannelDailyEntity = new TblRecordChannelDailyEntity();
                tblRecordChannelDailyEntity.setProfit(profit);
                tblRecordChannelDailyEntity.setRecharge(recharge);
                tblRecordChannelDailyEntity.setConversion(conversion);
                tblRecordChannelDailyEntity.setRegisterUsers(registerUsers);
                tblRecordChannelDailyEntity.setLoginUsers(loginUsers);
                tblRecordChannelDailyEntity.setTotalRevenue(totalRevenue);
                tblRecordChannelDailyEntity.setGameRecord(gameRecord);
                tblRecordChannelDailyEntity.setLastUpdateTime(new Date());

                tblRecordChannelDailyEntity.setCurrentDates(endOfDay);
                tblRecordChannelDailyEntity.setGold(gold);
                tblRecordChannelDailyEntity.setBankGold(bankGold);
                tblRecordChannelDailyEntity.setTotalGold(totalGold);
                tblRecordChannelDailyEntity.setPlatform(accountLoginType.getValue());
                tblRecordChannelDailyEntity.setAppChannel(accountRegisterChannel.getValue());
                tblRecordChannelDailyEntities.add(tblRecordChannelDailyEntity);
            }
        }



        if (count > 0) {
            tblRecordChannelDailyEntities.forEach(tblRecordChannelDailyEntity -> {
                tblRecordChannelDailyService.update(tblRecordChannelDailyEntity,new QueryWrapper<TblRecordChannelDailyEntity>().lambda()
                        .eq(TblRecordChannelDailyEntity::getAppChannel,tblRecordChannelDailyEntity.getAppChannel())
                        .eq(TblRecordChannelDailyEntity::getAppChannel,tblRecordChannelDailyEntity.getPlatform())
                        .eq(TblRecordChannelDailyEntity::getCurrentDates,endOfDay)
                );
            });
        }else {
            tblRecordChannelDailyService.saveBatch(tblRecordChannelDailyEntities);
        }


    }
}
