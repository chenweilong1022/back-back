package com.ozygod.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.game.service.TblPlayerLocationService;
import com.ozygod.model.zdgame.entity.TblPlayerLocationEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdlog.entity.TblRecordAccountOnlinePlayingEntity;
import com.ozygod.model.zdlog.service.TblRecordAccountOnlinePlayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 在线在玩玩家定时任务,每分钟统计当前在线和在玩人数
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-13 16:57
 */
@Configuration
@EnableScheduling
public class RecordAccountOnlineTask {



    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblPlayerLocationService tblPlayerLocationService;
    @Autowired
    private TblRecordAccountOnlinePlayingService tblRecordAccountOnlinePlayingService;

    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "0 0-59 * * * ?")
    public void configureTasks() {
        /**
         * 获取当前时间
         */
        DateTime date = DateUtil.date();

        DateTime currentMinutes = DateUtil.parseDateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:00"));


        /**
         * 在线数
         */
        int onlineNumber = tblAccountService.onlineNumber();

        /**
         * 在玩数
         */
        int playingNumber = tblPlayerLocationService.count(new QueryWrapper<TblPlayerLocationEntity>().lambda()
                .isNotNull(TblPlayerLocationEntity::getTable)
        );

        /**
         * 为当前分钟保存记录
         */
        TblRecordAccountOnlinePlayingEntity tblRecordAccountOnlinePlayingEntity = new TblRecordAccountOnlinePlayingEntity();
        tblRecordAccountOnlinePlayingEntity.setCurrentMinutes(currentMinutes);
        tblRecordAccountOnlinePlayingEntity.setOnlineNumber(onlineNumber);
        tblRecordAccountOnlinePlayingEntity.setPlayingNumber(playingNumber);
        tblRecordAccountOnlinePlayingService.save(tblRecordAccountOnlinePlayingEntity);

    }


}
