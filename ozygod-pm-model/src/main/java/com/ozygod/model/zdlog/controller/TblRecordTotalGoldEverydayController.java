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

import com.ozygod.model.zdlog.entity.TblRecordTotalGoldEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordTotalGoldEverydayListDto;
import com.ozygod.model.zdlog.service.TblRecordTotalGoldEverydayService;



/**
 * 每日总金币统计表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:38:17
 */
@RestController
@RequestMapping("zdlog/tblrecordtotalgoldeveryday")
public class TblRecordTotalGoldEverydayController {
    @Autowired
    private TblRecordTotalGoldEverydayService tblRecordTotalGoldEverydayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblRecordTotalGoldEverydayListDto tblRecordTotalGoldEveryday){
        return tblRecordTotalGoldEverydayService.queryPage(tblRecordTotalGoldEveryday);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblRecordTotalGoldEverydayEntity tblRecordTotalGoldEveryday = tblRecordTotalGoldEverydayService.getById(id);

        return ResponseBO.data(tblRecordTotalGoldEveryday);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblRecordTotalGoldEverydayEntity tblRecordTotalGoldEveryday){
			tblRecordTotalGoldEverydayService.save(tblRecordTotalGoldEveryday);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblRecordTotalGoldEverydayEntity tblRecordTotalGoldEveryday){
			tblRecordTotalGoldEverydayService.updateById(tblRecordTotalGoldEveryday);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblRecordTotalGoldEverydayService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
