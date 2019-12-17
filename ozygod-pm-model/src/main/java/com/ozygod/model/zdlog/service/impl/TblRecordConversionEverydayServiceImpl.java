package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordConversionEverydayDao;
import com.ozygod.model.zdlog.entity.TblRecordConversionEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordConversionEverydayListDto;
import com.ozygod.model.zdlog.service.TblRecordConversionEverydayService;


@Service("tblRecordConversionEverydayService")
public class TblRecordConversionEverydayServiceImpl extends ServiceImpl<TblRecordConversionEverydayDao, TblRecordConversionEverydayEntity> implements TblRecordConversionEverydayService {


    @Override
    public ResponseBO queryPage(TblRecordConversionEverydayListDto tblRecordConversionEveryday) {
        IPage<TblRecordConversionEverydayEntity> page = baseMapper.selectPage(
                tblRecordConversionEveryday.getPage(),
                new QueryWrapper<TblRecordConversionEverydayEntity>()
        );
        return ResponseBO.page(page);
    }
}
