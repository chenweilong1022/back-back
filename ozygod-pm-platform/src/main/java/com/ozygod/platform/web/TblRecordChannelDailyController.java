package com.ozygod.platform.web;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdlog.dto.TblRecordChannelDailyListDto;
import com.ozygod.model.zdlog.entity.TblRecordChannelDailyEntity;
import com.ozygod.model.zdlog.service.TblRecordChannelDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 渠道日报
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 11:11:19
 */
@RestController
@RequestMapping("zdlog/tblrecordchanneldaily")
public class TblRecordChannelDailyController {
    @Autowired
    private TblRecordChannelDailyService tblRecordChannelDailyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblRecordChannelDailyListDto tblRecordChannelDaily){

        if (ObjectUtil.isNotNull(tblRecordChannelDaily.getCurrentDates())) {
            DateTime endOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(tblRecordChannelDaily.getCurrentDates()));
            tblRecordChannelDaily.setCurrentDates(endOfDay);
        }else {
            DateTime endOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(DateUtil.date()));
            tblRecordChannelDaily.setCurrentDates(endOfDay);
        }
        return tblRecordChannelDailyService.queryPage(tblRecordChannelDaily);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblRecordChannelDailyEntity tblRecordChannelDaily = tblRecordChannelDailyService.getById(id);

        return ResponseBO.data(tblRecordChannelDaily);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblRecordChannelDailyEntity tblRecordChannelDaily){
			tblRecordChannelDailyService.save(tblRecordChannelDaily);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblRecordChannelDailyEntity tblRecordChannelDaily){
			tblRecordChannelDailyService.updateById(tblRecordChannelDaily);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblRecordChannelDailyService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
