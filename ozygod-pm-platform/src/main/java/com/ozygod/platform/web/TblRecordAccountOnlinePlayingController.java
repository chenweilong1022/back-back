package com.ozygod.platform.web;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdlog.entity.TblRecordAccountOnlinePlayingEntity;
import com.ozygod.platform.service.TblRecordAccountOnlinePlayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 玩家在线在玩记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-13 14:21:15
 */
@RestController
@RequestMapping("platform/tblrecordaccountonlineplaying")
public class TblRecordAccountOnlinePlayingController {

    @Autowired
    private TblRecordAccountOnlinePlayingService tblRecordAccountOnlinePlayingService;

    /**
     * 在线在玩玩家统计
     * 只统计当前时间往前4小时
     * @return
     */
    @GetMapping("listAllRecordAccountOnlinePlaying")
    public ResponseBO listAll() {

        //当前时间
        DateTime currentDate = DateUtil.date();
        //4小时前
        DateTime beginTime = DateUtil.offsetHour(currentDate, -4);
        /**
         * 查询时间段内所有在线在玩记录
         */
        List<TblRecordAccountOnlinePlayingEntity> list = tblRecordAccountOnlinePlayingService.list(new QueryWrapper<TblRecordAccountOnlinePlayingEntity>().lambda()
                .le(TblRecordAccountOnlinePlayingEntity::getCurrentMinutes, currentDate)
                .ge(TblRecordAccountOnlinePlayingEntity::getCurrentMinutes, beginTime)
        );
        return ResponseBO.data(list);
    }

}
