package com.ozygod.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.RedisKeys;
import com.ozygod.base.redis.IntegerRedisDao;
import com.ozygod.game.service.TblPlayerLocationService;
import com.ozygod.model.zdgame.entity.TblPlayerLocationEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdlog.entity.TblRecordAccountOnlinePlayingEntity;
import com.ozygod.model.zdlog.entity.TblRecordConversionEverydayEntity;
import com.ozygod.model.zdlog.service.TblRecordConversionEverydayService;
import com.ozygod.platform.service.TblRecordAccountOnlinePlayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 兑换数每日定时任务
 * 记录每日的兑换数和兑换总数量
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-13 16:57
 */
@Configuration
@EnableScheduling
public class RecordConversionEverydayTask {


    @Autowired
    private IntegerRedisDao integerRedisDao;
    @Autowired
    private TblRecordConversionEverydayService tblRecordConversionEverydayService;




    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "0 59 23 * * ?")
    public void configureTasks() {
        /**
         * 当日
         */
        String beginOfDay = DateUtil.formatDateTime(DateUtil.endOfDay(new Date()));

        /**
         * 昨天
         */
        String yesterday = DateUtil.formatDateTime(DateUtil.endOfDay(DateUtil.offsetDay(new Date(), -1)));
        /**
         * 当前兑换数
         */
        Integer totalCount = integerRedisDao.readFromJSONStr(RedisKeys.ONE.getValue());
        if (ObjectUtil.isNull(totalCount)) {
            totalCount = 0;
        }
        /**
         * 昨日兑换记录
         */
        TblRecordConversionEverydayEntity yesterdayOne = tblRecordConversionEverydayService.getOne(new QueryWrapper<TblRecordConversionEverydayEntity>().lambda()
                .eq(TblRecordConversionEverydayEntity::getCurrentDate, yesterday)
        );
        /**
         * 保存记录
         */
        TblRecordConversionEverydayEntity tblRecordConversionEverydayEntity = new TblRecordConversionEverydayEntity();
        tblRecordConversionEverydayEntity.setCurrentDate(DateUtil.parseDateTime(beginOfDay));
        tblRecordConversionEverydayEntity.setTodayConversion(0);
        if (ObjectUtil.isNotNull(yesterdayOne)) {
            tblRecordConversionEverydayEntity.setTodayConversion(totalCount - yesterdayOne.getTotalConversion());
        }
        tblRecordConversionEverydayEntity.setTotalConversion(totalCount);
        tblRecordConversionEverydayService.save(tblRecordConversionEverydayEntity);
    }


}
