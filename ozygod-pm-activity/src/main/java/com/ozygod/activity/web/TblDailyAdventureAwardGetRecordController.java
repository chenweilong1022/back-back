package com.ozygod.activity.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdlog.dto.TblDailyAdventureAwardGetRecordListDto;
import com.ozygod.model.zdlog.entity.TblDailyAdventureAwardGetRecordEntity;
import com.ozygod.model.zdlog.service.TblDailyAdventureAwardGetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 每日闯关记录领取表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-12 17:05:11
 */
@RestController
@RequestMapping("zdlog/tbldailyadventureawardgetrecord")
public class TblDailyAdventureAwardGetRecordController {
    @Autowired
    private TblDailyAdventureAwardGetRecordService tblDailyAdventureAwardGetRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblDailyAdventureAwardGetRecordListDto tblDailyAdventureAwardGetRecord){
        return tblDailyAdventureAwardGetRecordService.queryPage(tblDailyAdventureAwardGetRecord);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblDailyAdventureAwardGetRecordEntity tblDailyAdventureAwardGetRecord = tblDailyAdventureAwardGetRecordService.getById(id);

        return ResponseBO.data(tblDailyAdventureAwardGetRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblDailyAdventureAwardGetRecordEntity tblDailyAdventureAwardGetRecord){
			tblDailyAdventureAwardGetRecordService.save(tblDailyAdventureAwardGetRecord);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblDailyAdventureAwardGetRecordEntity tblDailyAdventureAwardGetRecord){
			tblDailyAdventureAwardGetRecordService.updateById(tblDailyAdventureAwardGetRecord);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblDailyAdventureAwardGetRecordService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
