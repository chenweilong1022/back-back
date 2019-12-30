package com.ozygod.platform.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdlog.dto.TblRecordDailyRechargeReportListDto;
import com.ozygod.model.zdlog.entity.TblRecordDailyRechargeReportEntity;
import com.ozygod.model.zdlog.service.TblRecordDailyRechargeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 每日充值报表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-30 14:37:31
 */
@RestController
@RequestMapping("zdlog/tblrecorddailyrechargereport")
public class TblRecordDailyRechargeReportController {
    @Autowired
    private TblRecordDailyRechargeReportService tblRecordDailyRechargeReportService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblRecordDailyRechargeReportListDto tblRecordDailyRechargeReport){
        return tblRecordDailyRechargeReportService.queryPage(tblRecordDailyRechargeReport);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Long id){
			TblRecordDailyRechargeReportEntity tblRecordDailyRechargeReport = tblRecordDailyRechargeReportService.getById(id);

        return ResponseBO.data(tblRecordDailyRechargeReport);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblRecordDailyRechargeReportEntity tblRecordDailyRechargeReport){
			tblRecordDailyRechargeReportService.save(tblRecordDailyRechargeReport);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblRecordDailyRechargeReportEntity tblRecordDailyRechargeReport){
			tblRecordDailyRechargeReportService.updateById(tblRecordDailyRechargeReport);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] ids){
			tblRecordDailyRechargeReportService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
