package com.ozygod.account.web;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdmanage.dto.TblMonitorListDto;
import com.ozygod.model.zdmanage.entity.TblMainframeMonitorLogEntity;
import com.ozygod.model.zdmanage.entity.TblMonitorEntity;
import com.ozygod.model.zdmanage.service.TblMainframeMonitorLogService;
import com.ozygod.model.zdmanage.service.TblMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 监听器表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-08 16:28:15
 */
@RestController
@RequestMapping("zdmanage/tblmonitor")
public class TblMonitorController {
    @Autowired
    private TblMonitorService tblMonitorService;

    @Autowired
    private TblMainframeMonitorLogService tblMainframeMonitorLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblMonitorListDto tblMonitor){
        return tblMonitorService.queryPage(tblMonitor);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblMonitorEntity tblMonitor = tblMonitorService.getById(id);

        return ResponseBO.data(tblMonitor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblMonitorEntity tblMonitor){
			tblMonitorService.save(tblMonitor);

        List<TblMainframeMonitorLogEntity> list = tblMainframeMonitorLogService.list(new QueryWrapper<TblMainframeMonitorLogEntity>());

        for (TblMainframeMonitorLogEntity tblMainframeMonitorLogEntity : list) {
            Map map = new HashMap();
            map.put("id",tblMonitor.getId());
            String url = URLUtil.formatUrl(tblMainframeMonitorLogEntity.getMainframe() + "/app/filemonitorSave");
            String post = HttpUtil.post(url, map, 3000);
        }
        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblMonitorEntity tblMonitor){
			tblMonitorService.updateById(tblMonitor);
        List<TblMainframeMonitorLogEntity> list = tblMainframeMonitorLogService.list(new QueryWrapper<TblMainframeMonitorLogEntity>());

        for (TblMainframeMonitorLogEntity tblMainframeMonitorLogEntity : list) {
            Map map = new HashMap();
            map.put("id",tblMonitor.getId());
            String url = URLUtil.formatUrl(tblMainframeMonitorLogEntity.getMainframe() + "/app/filemonitorFlush");
            String post = HttpUtil.post(url, map, 3000);
        }
        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public ResponseBO delete(@PathVariable("id") Integer id){
			tblMonitorService.removeById(id);


        List<TblMainframeMonitorLogEntity> list = tblMainframeMonitorLogService.list(new QueryWrapper<TblMainframeMonitorLogEntity>());
        for (TblMainframeMonitorLogEntity tblMainframeMonitorLogEntity : list) {
            Map map = new HashMap();
            map.put("id",id);
            String url = URLUtil.formatUrl(tblMainframeMonitorLogEntity.getMainframe() + "/app/filemonitorShut");
            String post = HttpUtil.post(url, map, 3000);
        }

        return ResponseBO.ok();
    }

}
