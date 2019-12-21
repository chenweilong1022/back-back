package com.ozygod.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.AccountLoginType;
import com.ozygod.base.enums.AccountLoginWay;
import com.ozygod.base.enums.AccountRegisterChannel;
import com.ozygod.base.enums.AccountRegisterType;
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
 * 每日注册定时任务
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 18:04
 */
@Configuration
@EnableScheduling
public class RecordAccountRegisterEverydayTask {

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
        DateTime beginOfDay = DateUtil.beginOfDay(DateUtil.date());
        /**
         * 今日最后一分钟
         */
        DateTime dateTime = DateUtil.parseDateTime(DateUtil.formatDateTime(date));

        /**
         * 查询是否已经统计过了
         */
        int count1 = tblRecordAccountLoginRegisterEverydayService.count(new QueryWrapper<TblRecordAccountLoginRegisterEverydayEntity>().lambda()
                .eq(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates, dateTime)
                .ge(TblRecordAccountLoginRegisterEverydayEntity::getType,AccountRegisterChannel.C3521.getKey())
        );

        /**
         * 注册渠道类型枚举
         */
        List<EnumVo> enumVos = EnumUtil.enumToVo(AccountRegisterChannel.values());
        List<EnumVo> enumVos1 = EnumUtil.enumToVo(AccountRegisterType.values());
        enumVos.addAll(enumVos1);

        /**
         * 用户每日登录记录list
         */
        List<TblRecordAccountLoginRegisterEverydayEntity> tblRecordAccountLoginRegisterEverydayEntities = new ArrayList<>();

        /**
         * 遍历
         */
        for (EnumVo enumVo : enumVos) {
            /**
             * 用户每日注册记录
             */
            TblRecordAccountLoginRegisterEverydayEntity tblRecordAccountLoginRegisterEverydayEntity = new TblRecordAccountLoginRegisterEverydayEntity();
            tblRecordAccountLoginRegisterEverydayEntity.setCurrentDates(dateTime);
            /**
             * 注册数量默认0
             */
            int count = 0;
            /**
             * 3521的注册数量
             */
            if(AccountRegisterChannel.C3521.getKey().equals(enumVo.getKey())) {
                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .eq(TblAccountEntity::getAppChannel, enumVo.getValue())
                        .gt(TblAccountEntity::getCreateTime,beginOfDay)
                        .gt(TblAccountEntity::getUserid,20000)
                );
                /**
                 * 非3521的注册数量
                 */
            }else if(AccountRegisterChannel.COTHER.getKey().equals(enumVo.getKey())) {

                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .notIn(TblAccountEntity::getAppChannel,AccountRegisterChannel.C3521.getValue())
                        .gt(TblAccountEntity::getCreateTime,beginOfDay)
                        .gt(TblAccountEntity::getUserid,20000)
                );
                /**
                 * 查询安卓注册的
                 */
            }else if(AccountRegisterType.IOS.getKey().equals(enumVo.getKey())) {

                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .eq(TblAccountEntity::getPlatform,enumVo.getValue())
                        .gt(TblAccountEntity::getCreateTime,beginOfDay)
                        .gt(TblAccountEntity::getUserid,20000)
                );
                /**
                 * 查询安卓注册的
                 */
            }else if(AccountRegisterType.ANDROID.getKey().equals(enumVo.getKey())) {
                count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .eq(TblAccountEntity::getPlatform, enumVo.getValue())
                        .gt(TblAccountEntity::getCreateTime,beginOfDay)
                        .gt(TblAccountEntity::getUserid,20000)
                );
            }
            /**
             * 设置然后添加到list中
             */
            tblRecordAccountLoginRegisterEverydayEntity.setCount(count);
            tblRecordAccountLoginRegisterEverydayEntity.setType(enumVo.getKey());
            tblRecordAccountLoginRegisterEverydayEntities.add(tblRecordAccountLoginRegisterEverydayEntity);
        }

        /**
         * 如果已经存在批量修改
         */
        if (count1 > 0) {
            for (TblRecordAccountLoginRegisterEverydayEntity tblRecordAccountLoginRegisterEverydayEntity : tblRecordAccountLoginRegisterEverydayEntities) {
                tblRecordAccountLoginRegisterEverydayService.update(tblRecordAccountLoginRegisterEverydayEntity,new QueryWrapper<TblRecordAccountLoginRegisterEverydayEntity>().lambda()
                        .eq(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates,tblRecordAccountLoginRegisterEverydayEntity.getCurrentDates())
                        .eq(TblRecordAccountLoginRegisterEverydayEntity::getType,tblRecordAccountLoginRegisterEverydayEntity.getType())
                );
            }
            /**
             * 否则批量保存
             */
        }else  {
            /**
             * 批量保存
             */
            tblRecordAccountLoginRegisterEverydayService.saveBatch(tblRecordAccountLoginRegisterEverydayEntities);
        }

    }
}
