package com.ozygod.account.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdmanage.dto.TblSysLogListDto;
import com.ozygod.model.zdmanage.entity.TblSysLogEntity;
import com.ozygod.model.zdmanage.service.TblSysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 系统日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-14 14:35:49
 */
@RestController
@RequestMapping("zdmanage/tblsyslog")
public class TblSysLogController {
    @Autowired
    private TblSysLogService tblSysLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblSysLogListDto tblSysLog){
        return tblSysLogService.queryPage(tblSysLog);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Long id){
			TblSysLogEntity tblSysLog = tblSysLogService.getById(id);

        return ResponseBO.data(tblSysLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblSysLogEntity tblSysLog){
			tblSysLogService.save(tblSysLog);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblSysLogEntity tblSysLog){
			tblSysLogService.updateById(tblSysLog);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] ids){
			tblSysLogService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
