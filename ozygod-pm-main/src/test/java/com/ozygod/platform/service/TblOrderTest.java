package com.ozygod.platform.service;
import java.util.ArrayList;
import java.util.Date;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ozygod.base.enums.AccountLoginType;
import com.ozygod.base.enums.AccountRegisterChannel;
import com.ozygod.base.enums.AppPayChannel;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.base.vo.EnumVo;
import com.ozygod.main.OzygodPmMainApplication;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdlog.entity.TblRecordRechargeAmountEverydayEntity;
import com.ozygod.model.zdlog.service.TblRecordAccountLoginRegisterEverydayService;
import com.ozygod.model.zdlog.service.TblRecordRechargeAmountEverydayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 13:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
public class TblOrderTest {

    @Autowired
    private TblRecordAccountLoginRegisterEverydayService tblRecordAccountLoginRegisterEverydayService;



    @Autowired
    private TblOrderService tblOrderService;

    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblRecordRechargeAmountEverydayService tblRecordRechargeAmountEverydayService;

    @Test
    public void t() {

        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetMonth(DateUtil.date(), -1), DateUtil.offsetDay(DateUtil.date(),-1), DateField.DAY_OF_MONTH);


        List<TblAccountEntity> list = tblAccountService.list(new QueryWrapper<TblAccountEntity>().lambda()
                .gt(TblAccountEntity::getUserid, 20000)
                .eq(TblAccountEntity::getAppChannel, AccountRegisterChannel.C3521.getValue())
        );


        List<EnumVo> enumVos = EnumUtil.enumToVo(AppPayChannel.values());

        List<TblOrderEntity> tblOrderEntities = new ArrayList<>();

        for (DateTime dateTime : dateTimes) {

            DateTime beginOfDay = DateUtil.beginOfDay(dateTime);


            for (int i = 0; i < 10; i++) {

                /**
                 * 随机支付方式
                 */
                EnumVo enumVo = CollUtil.get(enumVos, RandomUtil.randomInt(0, enumVos.size()));

                TblAccountEntity tblAccountEntity = CollUtil.get(list, RandomUtil.randomInt(0, list.size()));
                /**
                 * 随机支付日期
                 */
                DateTime dateTime1 = DateUtil.offsetMinute(beginOfDay, RandomUtil.randomInt(1, 1000));

                int i1 = RandomUtil.randomInt(1, 5000);


                TblOrderEntity tblOrderEntity = new TblOrderEntity();
//                tblOrderEntity.setId(0L);
                tblOrderEntity.setUserid(tblAccountEntity.getUserid());
                tblOrderEntity.setGoodsId("GOODS_WX_20191219");
                tblOrderEntity.setChannel(enumVo.getValue());
                tblOrderEntity.setOrderId(RandomUtil.simpleUUID().toUpperCase());
                tblOrderEntity.setMoney(i1 * 100);
                tblOrderEntity.setState(3);
                tblOrderEntity.setCreateTime(new Date());
                tblOrderEntity.setInvalidTime(new Date());
                tblOrderEntity.setPayTime(dateTime1);
                tblOrderEntity.setCompleteTime(dateTime1);

                tblOrderEntities.add(tblOrderEntity);
            }
        }
        tblOrderService.saveBatch(tblOrderEntities);

    }

    @Test
    public void t2() {


        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetMonth(DateUtil.date(), -1), DateUtil.offsetDay(DateUtil.date(),-1), DateField.DAY_OF_MONTH);


        for (DateTime dateTime2 : dateTimes) {


            DateTime date = DateUtil.endOfDay(dateTime2);
            DateTime beginOfDay = DateUtil.beginOfDay(dateTime2);
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
                            .gt(TblAccountEntity::getUserid, 20000)
                            .eq(TblAccountEntity::getAppChannel, accountRegisterChannel.getValue())
                            .eq(TblAccountEntity::getPlatform, accountLoginType.getValue())
                    );
                    if (CollUtil.isEmpty(list)) {
                        continue;
                    }

                    List<Long> userids = list.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());
                    for (AppPayChannel appPayChannel : appPayChannels) {

                        List<TblOrderEntity> tblOrderEntities = tblOrderService.list(new QueryWrapper<TblOrderEntity>().lambda()
                                .in(TblOrderEntity::getUserid, userids)
                                .eq(TblOrderEntity::getChannel, appPayChannel.getValue())
                                .gt(TblOrderEntity::getPayTime,beginOfDay)
                        );
                        if (CollUtil.isEmpty(tblOrderEntities)) {
                            continue;
                        }

                        /**
                         * 当前的总金额
                         */
                        int sum = tblOrderEntities.stream().mapToInt(TblOrderEntity::getMoney).sum();

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


}
