package com.ozygod.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ozygod.base.enums.AccountLoginType;
import com.ozygod.base.enums.AccountRegisterChannel;
import com.ozygod.base.enums.AppPayChannel;
import com.ozygod.base.enums.Global;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdlog.entity.TblRecordRechargeAmountEverydayEntity;
import com.ozygod.model.zdlog.service.TblRecordRechargeAmountEverydayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 充值金额每日统计
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 15:32
 */
@Configuration
@EnableScheduling
public class RecordRechargeAmountEverydayTask {


    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblOrderService tblOrderService;
    @Autowired
    private TblRecordRechargeAmountEverydayService tblRecordRechargeAmountEverydayService;

    /**
     * 每小时统计一次
     */
    @Scheduled(cron = "0 0 0-23 * * ?")
    public void configureTasks() {


        DateTime date = DateUtil.endOfDay(DateUtil.date());
        DateTime beginOfDay = DateUtil.beginOfDay(DateUtil.date());
        /**
         * 今日最后一分钟
         */
        DateTime dateTime = DateUtil.parseDateTime(DateUtil.formatDateTime(date));


        AccountRegisterChannel[] accountRegisterChannels = AccountRegisterChannel.values();
        AccountLoginType[] accountLoginTypes = AccountLoginType.values();
        AppPayChannel[] appPayChannels = AppPayChannel.values();


        int count = tblRecordRechargeAmountEverydayService.count(new QueryWrapper<TblRecordRechargeAmountEverydayEntity>().lambda()
                .eq(TblRecordRechargeAmountEverydayEntity::getCurrentDates, dateTime)
        );


        List<TblRecordRechargeAmountEverydayEntity> tblRecordRechargeAmountEverydayEntities = new ArrayList<>();

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

                List<Long> userids = list.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());
                for (AppPayChannel appPayChannel : appPayChannels) {

                    List<TblOrderEntity> tblOrderEntities = tblOrderService.list(new QueryWrapper<TblOrderEntity>().lambda()
                            .in(TblOrderEntity::getUserid, userids)
                            .eq(TblOrderEntity::getChannel, appPayChannel.getValue())
                            .gt(TblOrderEntity::getPayTime,beginOfDay)
                    );
                    int sum = 0;

                    if (CollUtil.isNotEmpty(tblOrderEntities)) {
                        /**
                         * 当前的总金额
                         */
                        sum = tblOrderEntities.stream().mapToInt(TblOrderEntity::getMoney).sum();
                    }


                    TblRecordRechargeAmountEverydayEntity tblRecordRechargeAmountEverydayEntity = new TblRecordRechargeAmountEverydayEntity();
                    tblRecordRechargeAmountEverydayEntity.setCurrentDates(dateTime);
                    tblRecordRechargeAmountEverydayEntity.setWeek(DateUtil.weekOfYear(dateTime));
                    tblRecordRechargeAmountEverydayEntity.setMonth(DateUtil.month(dateTime));
                    tblRecordRechargeAmountEverydayEntity.setMoney(sum);
                    tblRecordRechargeAmountEverydayEntity.setPayChannel(appPayChannel.getValue());
                    tblRecordRechargeAmountEverydayEntity.setPlatform(accountLoginType.getValue());
                    tblRecordRechargeAmountEverydayEntity.setAppChannel(accountRegisterChannel.getValue());
                    tblRecordRechargeAmountEverydayEntities.add(tblRecordRechargeAmountEverydayEntity);
                }

            }
        }

        if (count > 0) {
            tblRecordRechargeAmountEverydayEntities.forEach(tblRecordRechargeAmountEverydayEntity -> {
                tblRecordRechargeAmountEverydayService.update(tblRecordRechargeAmountEverydayEntity,new UpdateWrapper<TblRecordRechargeAmountEverydayEntity>().lambda()
                        .eq(TblRecordRechargeAmountEverydayEntity::getCurrentDates,dateTime)
                        .eq(TblRecordRechargeAmountEverydayEntity::getAppChannel,tblRecordRechargeAmountEverydayEntity.getAppChannel())
                        .eq(TblRecordRechargeAmountEverydayEntity::getPayChannel,tblRecordRechargeAmountEverydayEntity.getPayChannel())
                        .eq(TblRecordRechargeAmountEverydayEntity::getPlatform,tblRecordRechargeAmountEverydayEntity.getPlatform())
                );
            });
        }else {
            tblRecordRechargeAmountEverydayService.saveBatch(tblRecordRechargeAmountEverydayEntities);
        }




    }

}
