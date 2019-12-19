package com.ozygod.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.AccountLoginType;
import com.ozygod.base.enums.AccountLoginWay;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.base.vo.EnumVo;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdlog.entity.TblRecordAccountLoginRegisterEverydayEntity;
import com.ozygod.model.zdlog.service.TblRecordAccountLoginRegisterEverydayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户每日登录定时任务
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 10:51
 */
@Configuration
@EnableScheduling
public class RecordAccountLoginEverydayTask {

    @Autowired
    private TblAccountService tblAccountService;

    @Autowired
    private TblRecordAccountLoginRegisterEverydayService tblRecordAccountLoginRegisterEverydayService;

    /**
     * 每小时统计一次
     */
    @Scheduled(cron = "0 0 0-23 * * ?")
    public void configureTasks() {

        DateTime date = DateUtil.endOfDay(DateUtil.date());
        /**
         * 今日最后一分钟
         */
        DateTime dateTime = DateUtil.parseDateTime(DateUtil.formatDateTime(date));

        /**
         * 查询是否已经统计过了
         */
        int count1 = tblRecordAccountLoginRegisterEverydayService.count(new QueryWrapper<TblRecordAccountLoginRegisterEverydayEntity>().lambda()
                .eq(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates, dateTime)
        );

        /**
         * 登录注册渠道类型枚举
         */
        List<EnumVo> enumVos = EnumUtil.enumToVo(AccountLoginWay.values());
        List<EnumVo> enumVos1 = EnumUtil.enumToVo(AccountLoginType.values());
        enumVos.addAll(enumVos1);

        /**
         * 用户每日登录记录list
         */
        List<TblRecordAccountLoginRegisterEverydayEntity> tblRecordAccountLoginEverydayEntities = new ArrayList<>();

        /**
         * 遍历
         */
        for (EnumVo enumVo : enumVos) {
            /**
             * 用户每日登录记录
             */
            TblRecordAccountLoginRegisterEverydayEntity tblRecordAccountLoginEverydayEntity = new TblRecordAccountLoginRegisterEverydayEntity();
            tblRecordAccountLoginEverydayEntity.setCurrentDates(dateTime);
            /**
             * 登录数量默认0
             */
            int count = 0;
            /**
             * 查询快速登录的人
             */
            if(AccountLoginWay.GUEST.getKey().equals(enumVo.getKey())) {
                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .eq(TblAccountEntity::getChannel, enumVo.getValue())
                        .gt(TblAccountEntity::getUserid,20000)
                );
                /**
                 * 查询非快速登录的
                 */
            }else if(AccountLoginWay.OTHER.getKey().equals(enumVo.getKey())) {

                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .notIn(TblAccountEntity::getChannel, AccountLoginWay.GUEST.getValue())
                        .gt(TblAccountEntity::getUserid,20000)
                );
                /**
                 * 查询ios登录的
                 */
            }else if(AccountLoginType.IOS.getKey().equals(enumVo.getKey())) {

                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .eq(TblAccountEntity::getPlatform,enumVo.getValue())
                        .gt(TblAccountEntity::getUserid,20000)
                );
                /**
                 * 查询安卓登录的
                 */
            }else if(AccountLoginType.ANDROID.getKey().equals(enumVo.getKey())) {
                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .eq(TblAccountEntity::getPlatform, enumVo.getValue())
                        .gt(TblAccountEntity::getUserid,20000)
                );
            }
            /**
             * 设置然后添加到list中
             */
            tblRecordAccountLoginEverydayEntity.setCount(count);
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
