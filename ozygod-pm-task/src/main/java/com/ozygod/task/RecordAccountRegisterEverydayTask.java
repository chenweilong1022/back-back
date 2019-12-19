package com.ozygod.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 每日注册定时任务
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 18:04
 */
@Configuration
@EnableScheduling
public class RecordAccountRegisterEverydayTask {


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




    }
}
