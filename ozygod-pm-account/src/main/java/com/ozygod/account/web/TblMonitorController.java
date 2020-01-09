package com.ozygod.account.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdmanage.dto.TblMonitorListDto;
import com.ozygod.model.zdmanage.entity.TblMonitorEntity;
import com.ozygod.model.zdmanage.service.TblMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


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

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblMonitorEntity tblMonitor){
			tblMonitorService.updateById(tblMonitor);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblMonitorService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
