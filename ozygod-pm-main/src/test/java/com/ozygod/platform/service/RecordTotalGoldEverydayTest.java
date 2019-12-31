package com.ozygod.platform.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.ozygod.base.enums.AccountLoginType;
import com.ozygod.base.enums.AccountRegisterChannel;
import com.ozygod.main.OzygodPmMainApplication;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblPlayerinfoService;
import com.ozygod.model.zdlog.entity.TblRecordChannelDailyEntity;
import com.ozygod.model.zdlog.service.TblRecordChannelDailyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 14:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
public class RecordTotalGoldEverydayTest {


    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblRecordChannelDailyService tblRecordChannelDailyService;
    @Autowired
    private TblPlayerinfoService tblPlayerinfoService;

    @Test
    public void save() {



        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetMonth(DateUtil.date(), -1), DateUtil.offsetDay(DateUtil.date(),-1), DateField.DAY_OF_MONTH);

        AccountRegisterChannel[] accountRegisterChannels = AccountRegisterChannel.values();
        AccountLoginType[] accountLoginTypes = AccountLoginType.values();

        List<TblRecordChannelDailyEntity> tblRecordTotalGoldEverydayEntities = new ArrayList();

        for (DateTime dateTime2 : dateTimes) {


            DateTime date = DateUtil.endOfDay(dateTime2);
            DateTime beginOfDay = DateUtil.beginOfDay(dateTime2);
            /**
             * 今日最后一分钟
             */
            DateTime dateTime = DateUtil.parseDateTime(DateUtil.formatDateTime(date));

//            System.out.println(dateTime);


            for (AccountRegisterChannel accountRegisterChannel : accountRegisterChannels) {
                //暂时不处理其他渠道
                if(accountRegisterChannel.getKey().equals(AccountRegisterChannel.COTHER.getKey())) {
                    continue;
                }
                for (AccountLoginType accountLoginType : accountLoginTypes) {

                    long gold = RandomUtil.randomLong(100000, 200000);
                    long bankGold = RandomUtil.randomLong(10000, 20000);

                    long totalGold = gold + bankGold;


                    TblRecordChannelDailyEntity tblRecordTotalGoldEverydayEntity = new TblRecordChannelDailyEntity();
                    tblRecordTotalGoldEverydayEntity.setCurrentDates(dateTime);
                    tblRecordTotalGoldEverydayEntity.setGold(gold);
                    tblRecordTotalGoldEverydayEntity.setBankGold(bankGold);
                    tblRecordTotalGoldEverydayEntity.setTotalGold(totalGold);
                    tblRecordTotalGoldEverydayEntity.setPlatform(accountLoginType.getValue());
                    tblRecordTotalGoldEverydayEntity.setAppChannel(accountRegisterChannel.getValue());
                    tblRecordTotalGoldEverydayEntities.add(tblRecordTotalGoldEverydayEntity);

                }
            }
        }

        tblRecordChannelDailyService.saveBatch(tblRecordTotalGoldEverydayEntities,1000);
    }



}
