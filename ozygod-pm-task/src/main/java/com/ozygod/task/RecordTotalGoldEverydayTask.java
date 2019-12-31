package com.ozygod.task;

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
import com.ozygod.model.zdgame.service.TblPlayerinfoService;
import com.ozygod.model.zdlog.entity.TblRecordChannelDailyEntity;
import com.ozygod.model.zdlog.service.TblRecordChannelDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 每日总金币定时任务
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
        DateTime dateTime = DateUtil.parseDateTime(DateUtil.formatDateTime(date));



        AccountRegisterChannel[] accountRegisterChannels = AccountRegisterChannel.values();
        AccountLoginType[] accountLoginTypes = AccountLoginType.values();


        int count = tblRecordChannelDailyService.count(new QueryWrapper<TblRecordChannelDailyEntity>().lambda()
                .eq(TblRecordChannelDailyEntity::getCurrentDates, dateTime)
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

                TblRecordChannelDailyEntity tblRecordChannelDailyEntity = new TblRecordChannelDailyEntity();
                tblRecordChannelDailyEntity.setCurrentDates(dateTime);
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
                        .eq(TblRecordChannelDailyEntity::getCurrentDates,dateTime)
                );
            });
        }else {
            tblRecordChannelDailyService.saveBatch(tblRecordChannelDailyEntities);
        }


    }
}
