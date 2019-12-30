package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblRecordDailyRechargeReportEntity;
import com.ozygod.model.zdlog.dto.TblRecordDailyRechargeReportListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 每日充值报表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-30 14:37:31
 */
public interface TblRecordDailyRechargeReportService extends IService<TblRecordDailyRechargeReportEntity> {

    ResponseBO queryPage(TblRecordDailyRechargeReportListDto tblRecordDailyRechargeReport);

}

