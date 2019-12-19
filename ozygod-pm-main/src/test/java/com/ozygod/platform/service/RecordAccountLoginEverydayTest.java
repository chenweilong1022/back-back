package com.ozygod.platform.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.AccountLoginWay;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.base.vo.EnumVo;
import com.ozygod.main.OzygodPmMainApplication;
import com.ozygod.model.zdlog.entity.TblRecordAccountLoginRegisterEverydayEntity;
import com.ozygod.model.zdlog.service.TblRecordAccountLoginRegisterEverydayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 13:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
public class RecordAccountLoginEverydayTest {

    @Autowired
    private TblRecordAccountLoginRegisterEverydayService tblRecordAccountLoginRegisterEverydayService;

    @Test
    public void save() {
        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetMonth(new Date(), -1),DateUtil.offsetDay(new Date(),-1), DateField.DAY_OF_MONTH);

        /**
         * 登录注册渠道类型枚举
         */
        List<EnumVo> enumVos = EnumUtil.enumToVo(AccountLoginWay.values());


        for (DateTime dateTime : dateTimes) {


            /**
             * 用户每日登录记录list
             */
            List<TblRecordAccountLoginRegisterEverydayEntity> tblRecordAccountLoginEverydayEntities = new ArrayList<>();
            DateTime date = DateUtil.endOfDay(dateTime);
            /**
             * 今日最后一分钟
             */
            DateTime dateTime1 = DateUtil.parseDateTime(DateUtil.formatDateTime(date));


            /**
             * 查询是否已经统计过了
             */
            int count1 = tblRecordAccountLoginRegisterEverydayService.count(new QueryWrapper<TblRecordAccountLoginRegisterEverydayEntity>().lambda()
                    .eq(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates, dateTime1)
            );


            System.out.println(dateTime1);
            /**
             * 遍历
             */
            for (EnumVo enumVo : enumVos) {
                /**
                 * 用户每日登录记录
                 */
                TblRecordAccountLoginRegisterEverydayEntity tblRecordAccountLoginEverydayEntity = new TblRecordAccountLoginRegisterEverydayEntity();
                tblRecordAccountLoginEverydayEntity.setCurrentDates(dateTime1);
                tblRecordAccountLoginEverydayEntity.setCount(RandomUtil.randomInt(1,100));
                tblRecordAccountLoginEverydayEntity.setType(enumVo.getKey());
                tblRecordAccountLoginEverydayEntities.add(tblRecordAccountLoginEverydayEntity);
            }

            /**
             * 如果已经存在批量修改
             */
            if (count1 > 0) {
                for (TblRecordAccountLoginRegisterEverydayEntity tblRecordAccountLoginEverydayEntity : tblRecordAccountLoginEverydayEntities) {
                    tblRecordAccountLoginRegisterEverydayService.update(tblRecordAccountLoginEverydayEntity,new QueryWrapper<TblRecordAccountLoginRegisterEverydayEntity>().lambda()
                            .eq(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates,tblRecordAccountLoginEverydayEntity.getCurrentDates())
                            .eq(TblRecordAccountLoginRegisterEverydayEntity::getType,tblRecordAccountLoginEverydayEntity.getType())
                    );
                }
                /**
                 * 否则批量修改
                 */
            }else  {
                /**
                 * 批量保存
                 */
                tblRecordAccountLoginRegisterEverydayService.saveBatch(tblRecordAccountLoginEverydayEntities);
            }

        }




    }

}
