package com.ozygod.model.zdlog.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.ozygod.base.bo.ResponseBO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozygod.model.zdlog.entity.TblGameRecordEntity;
import com.ozygod.model.zdlog.dto.TblGameRecordListDto;
import com.ozygod.model.zdlog.service.TblGameRecordService;



/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 14:29:10
 */
@RestController
@RequestMapping("zdlog/tblgamerecord")
public class TblGameRecordController {
    @Autowired
    private TblGameRecordService tblGameRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblGameRecordListDto tblGameRecord){
        return tblGameRecordService.queryPage(tblGameRecord);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblGameRecordEntity tblGameRecord = tblGameRecordService.getById(id);

        return ResponseBO.data(tblGameRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblGameRecordEntity tblGameRecord){
			tblGameRecordService.save(tblGameRecord);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblGameRecordEntity tblGameRecord){
			tblGameRecordService.updateById(tblGameRecord);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblGameRecordService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
