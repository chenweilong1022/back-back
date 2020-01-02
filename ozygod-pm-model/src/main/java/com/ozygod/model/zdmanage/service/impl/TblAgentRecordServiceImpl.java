package com.ozygod.model.zdmanage.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdmanage.dao.TblAgentRecordDao;
import com.ozygod.model.zdmanage.entity.TblAgentRecordEntity;
import com.ozygod.model.zdmanage.dto.TblAgentRecordListDto;
import com.ozygod.model.zdmanage.service.TblAgentRecordService;


@Service("tblAgentRecordService")
public class TblAgentRecordServiceImpl extends ServiceImpl<TblAgentRecordDao, TblAgentRecordEntity> implements TblAgentRecordService {


    @Override
    public ResponseBO queryPage(TblAgentRecordListDto tblAgentRecord) {
        IPage<TblAgentRecordEntity> page = baseMapper.selectPage(
                tblAgentRecord.getPage(),
                new QueryWrapper<TblAgentRecordEntity>()
        );
        return ResponseBO.page(page);
    }
}
