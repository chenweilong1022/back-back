package com.ozygod.model.zdmanage.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdmanage.dao.TblMainframeMonitorLogDao;
import com.ozygod.model.zdmanage.entity.TblMainframeMonitorLogEntity;
import com.ozygod.model.zdmanage.dto.TblMainframeMonitorLogListDto;
import com.ozygod.model.zdmanage.service.TblMainframeMonitorLogService;


@Service("tblMainframeMonitorLogService")
public class TblMainframeMonitorLogServiceImpl extends ServiceImpl<TblMainframeMonitorLogDao, TblMainframeMonitorLogEntity> implements TblMainframeMonitorLogService {


    @Override
    public ResponseBO queryPage(TblMainframeMonitorLogListDto tblMainframeMonitorLog) {
        IPage<TblMainframeMonitorLogEntity> page = baseMapper.selectPage(
                tblMainframeMonitorLog.getPage(),
                new QueryWrapper<TblMainframeMonitorLogEntity>()
        );
        return ResponseBO.page(page);
    }
}
