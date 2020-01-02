package com.ozygod.model.zdmanage.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.ozygod.base.bo.ResponseBO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozygod.model.zdmanage.entity.TblAgentRecordEntity;
import com.ozygod.model.zdmanage.dto.TblAgentRecordListDto;
import com.ozygod.model.zdmanage.service.TblAgentRecordService;



/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 14:17:46
 */
@RestController
@RequestMapping("zdmanage/tblagentrecord")
public class TblAgentRecordController {
    @Autowired
    private TblAgentRecordService tblAgentRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblAgentRecordListDto tblAgentRecord){
        return tblAgentRecordService.queryPage(tblAgentRecord);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Long id){
			TblAgentRecordEntity tblAgentRecord = tblAgentRecordService.getById(id);

        return ResponseBO.data(tblAgentRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblAgentRecordEntity tblAgentRecord){
			tblAgentRecordService.save(tblAgentRecord);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblAgentRecordEntity tblAgentRecord){
			tblAgentRecordService.updateById(tblAgentRecord);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] ids){
			tblAgentRecordService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
