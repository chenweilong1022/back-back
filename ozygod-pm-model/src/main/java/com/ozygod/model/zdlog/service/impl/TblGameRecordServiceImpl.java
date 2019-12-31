package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblGameRecordDao;
import com.ozygod.model.zdlog.entity.TblGameRecordEntity;
import com.ozygod.model.zdlog.dto.TblGameRecordListDto;
import com.ozygod.model.zdlog.service.TblGameRecordService;


@Service("tblGameRecordService")
public class TblGameRecordServiceImpl extends ServiceImpl<TblGameRecordDao, TblGameRecordEntity> implements TblGameRecordService {


    @Override
    public ResponseBO queryPage(TblGameRecordListDto tblGameRecord) {
        IPage<TblGameRecordEntity> page = baseMapper.selectPage(
                tblGameRecord.getPage(),
                new QueryWrapper<TblGameRecordEntity>()
        );
        return ResponseBO.page(page);
    }
}
