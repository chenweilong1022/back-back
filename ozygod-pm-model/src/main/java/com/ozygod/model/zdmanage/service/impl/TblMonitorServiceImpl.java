package com.ozygod.model.zdmanage.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdmanage.dao.TblMonitorDao;
import com.ozygod.model.zdmanage.entity.TblMonitorEntity;
import com.ozygod.model.zdmanage.dto.TblMonitorListDto;
import com.ozygod.model.zdmanage.service.TblMonitorService;


@Service("tblMonitorService")
public class TblMonitorServiceImpl extends ServiceImpl<TblMonitorDao, TblMonitorEntity> implements TblMonitorService {


    @Override
    public ResponseBO queryPage(TblMonitorListDto tblMonitor) {
        IPage<TblMonitorEntity> page = baseMapper.selectPage(
                tblMonitor.getPage(),
                new QueryWrapper<TblMonitorEntity>()
        );
        return ResponseBO.page(page);
    }
}
