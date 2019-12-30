package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordDailyRechargeReportDao;
import com.ozygod.model.zdlog.entity.TblRecordDailyRechargeReportEntity;
import com.ozygod.model.zdlog.dto.TblRecordDailyRechargeReportListDto;
import com.ozygod.model.zdlog.service.TblRecordDailyRechargeReportService;


@Service("tblRecordDailyRechargeReportService")
public class TblRecordDailyRechargeReportServiceImpl extends ServiceImpl<TblRecordDailyRechargeReportDao, TblRecordDailyRechargeReportEntity> implements TblRecordDailyRechargeReportService {


    @Override
    public ResponseBO queryPage(TblRecordDailyRechargeReportListDto tblRecordDailyRechargeReport) {
        IPage<TblRecordDailyRechargeReportEntity> page = baseMapper.selectPage(
                tblRecordDailyRechargeReport.getPage(),
                new QueryWrapper<TblRecordDailyRechargeReportEntity>()
        );
        return ResponseBO.page(page);
    }
}
