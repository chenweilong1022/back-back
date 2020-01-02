package com.ozygod.model.zdmanage.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdmanage.dao.TblAgentRealtimeDao;
import com.ozygod.model.zdmanage.entity.TblAgentRealtimeEntity;
import com.ozygod.model.zdmanage.dto.TblAgentRealtimeListDto;
import com.ozygod.model.zdmanage.service.TblAgentRealtimeService;


@Service("tblAgentRealtimeService")
public class TblAgentRealtimeServiceImpl extends ServiceImpl<TblAgentRealtimeDao, TblAgentRealtimeEntity> implements TblAgentRealtimeService {


    @Override
    public ResponseBO queryPage(TblAgentRealtimeListDto tblAgentRealtime) {
        IPage<TblAgentRealtimeEntity> page = baseMapper.selectPage(
                tblAgentRealtime.getPage(),
                new QueryWrapper<TblAgentRealtimeEntity>()
        );
        return ResponseBO.page(page);
    }
}
