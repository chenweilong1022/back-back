package com.ozygod.model.zdmanage.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdmanage.dao.TblSysLogDao;
import com.ozygod.model.zdmanage.entity.TblSysLogEntity;
import com.ozygod.model.zdmanage.dto.TblSysLogListDto;
import com.ozygod.model.zdmanage.service.TblSysLogService;


@Service("tblSysLogService")
public class TblSysLogServiceImpl extends ServiceImpl<TblSysLogDao, TblSysLogEntity> implements TblSysLogService {


    @Override
    public ResponseBO queryPage(TblSysLogListDto tblSysLog) {
        IPage<TblSysLogEntity> page = baseMapper.selectPage(
                tblSysLog.getPage(),
                new QueryWrapper<TblSysLogEntity>()
        );
        return ResponseBO.page(page);
    }
}
