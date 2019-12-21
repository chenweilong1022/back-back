package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordTotalGoldEverydayDao;
import com.ozygod.model.zdlog.entity.TblRecordTotalGoldEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordTotalGoldEverydayListDto;
import com.ozygod.model.zdlog.service.TblRecordTotalGoldEverydayService;


@Service("tblRecordTotalGoldEverydayService")
public class TblRecordTotalGoldEverydayServiceImpl extends ServiceImpl<TblRecordTotalGoldEverydayDao, TblRecordTotalGoldEverydayEntity> implements TblRecordTotalGoldEverydayService {


    @Override
    public ResponseBO queryPage(TblRecordTotalGoldEverydayListDto tblRecordTotalGoldEveryday) {
        IPage<TblRecordTotalGoldEverydayEntity> page = baseMapper.selectPage(
                tblRecordTotalGoldEveryday.getPage(),
                new QueryWrapper<TblRecordTotalGoldEverydayEntity>()
        );
        return ResponseBO.page(page);
    }
}
