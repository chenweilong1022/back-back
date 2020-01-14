package com.ozygod.model.zdmanage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdmanage.dao.TblSysLogDao;
import com.ozygod.model.zdmanage.dto.TblSysLogListDto;
import com.ozygod.model.zdmanage.entity.TblSysLogEntity;
import com.ozygod.model.zdmanage.service.TblSysLogService;
import org.springframework.stereotype.Service;


@Service("tblSysLogService")
public class TblSysLogServiceImpl extends ServiceImpl<TblSysLogDao, TblSysLogEntity> implements TblSysLogService {


    @Override
    public ResponseBO queryPage(TblSysLogListDto tblSysLog) {
        IPage<TblSysLogEntity> page = baseMapper.selectPage(
                tblSysLog.getPage(),
                new QueryWrapper<TblSysLogEntity>().lambda()
                .orderByDesc(TblSysLogEntity::getCreateTime)
                .like(StrUtil.isNotBlank(tblSysLog.getHostUrl()),TblSysLogEntity::getHostUrl,tblSysLog.getHostUrl())
        );
        return ResponseBO.page(page);
    }
}
