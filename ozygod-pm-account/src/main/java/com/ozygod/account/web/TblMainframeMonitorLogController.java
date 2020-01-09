package com.ozygod.account.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdmanage.dto.TblMainframeMonitorLogListDto;
import com.ozygod.model.zdmanage.entity.TblMainframeMonitorLogEntity;
import com.ozygod.model.zdmanage.service.TblMainframeMonitorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 所有监控的日志主机
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-08 16:28:15
 */
@RestController
@RequestMapping("zdmanage/tblmainframemonitorlog")
public class TblMainframeMonitorLogController {
    @Autowired
    private TblMainframeMonitorLogService tblMainframeMonitorLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblMainframeMonitorLogListDto tblMainframeMonitorLog){
        return tblMainframeMonitorLogService.queryPage(tblMainframeMonitorLog);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblMainframeMonitorLogEntity tblMainframeMonitorLog = tblMainframeMonitorLogService.getById(id);

        return ResponseBO.data(tblMainframeMonitorLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblMainframeMonitorLogEntity tblMainframeMonitorLog){
			tblMainframeMonitorLogService.save(tblMainframeMonitorLog);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblMainframeMonitorLogEntity tblMainframeMonitorLog){
			tblMainframeMonitorLogService.updateById(tblMainframeMonitorLog);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblMainframeMonitorLogService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
