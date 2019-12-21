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

import com.ozygod.model.zdlog.entity.TblRecordRechargeAmountEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordRechargeAmountEverydayListDto;
import com.ozygod.model.zdlog.service.TblRecordRechargeAmountEverydayService;



/**
 * 每日充值记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 15:27:02
 */
@RestController
@RequestMapping("zdlog/tblrecordrechargeamounteveryday")
public class TblRecordRechargeAmountEverydayController {
    @Autowired
    private TblRecordRechargeAmountEverydayService tblRecordRechargeAmountEverydayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblRecordRechargeAmountEverydayListDto tblRecordRechargeAmountEveryday){
        return tblRecordRechargeAmountEverydayService.queryPage(tblRecordRechargeAmountEveryday);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblRecordRechargeAmountEverydayEntity tblRecordRechargeAmountEveryday = tblRecordRechargeAmountEverydayService.getById(id);

        return ResponseBO.data(tblRecordRechargeAmountEveryday);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblRecordRechargeAmountEverydayEntity tblRecordRechargeAmountEveryday){
			tblRecordRechargeAmountEverydayService.save(tblRecordRechargeAmountEveryday);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblRecordRechargeAmountEverydayEntity tblRecordRechargeAmountEveryday){
			tblRecordRechargeAmountEverydayService.updateById(tblRecordRechargeAmountEveryday);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblRecordRechargeAmountEverydayService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
