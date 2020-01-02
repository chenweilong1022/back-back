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

import com.ozygod.model.zdlog.entity.TblRecordChannelGeneralizeEntity;
import com.ozygod.model.zdlog.dto.TblRecordChannelGeneralizeListDto;
import com.ozygod.model.zdlog.service.TblRecordChannelGeneralizeService;



/**
 * 每日渠道推广统计
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 13:54:15
 */
@RestController
@RequestMapping("zdlog/tblrecordchannelgeneralize")
public class TblRecordChannelGeneralizeController {
    @Autowired
    private TblRecordChannelGeneralizeService tblRecordChannelGeneralizeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblRecordChannelGeneralizeListDto tblRecordChannelGeneralize){
        return tblRecordChannelGeneralizeService.queryPage(tblRecordChannelGeneralize);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Long id){
			TblRecordChannelGeneralizeEntity tblRecordChannelGeneralize = tblRecordChannelGeneralizeService.getById(id);

        return ResponseBO.data(tblRecordChannelGeneralize);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblRecordChannelGeneralizeEntity tblRecordChannelGeneralize){
			tblRecordChannelGeneralizeService.save(tblRecordChannelGeneralize);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblRecordChannelGeneralizeEntity tblRecordChannelGeneralize){
			tblRecordChannelGeneralizeService.updateById(tblRecordChannelGeneralize);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] ids){
			tblRecordChannelGeneralizeService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
