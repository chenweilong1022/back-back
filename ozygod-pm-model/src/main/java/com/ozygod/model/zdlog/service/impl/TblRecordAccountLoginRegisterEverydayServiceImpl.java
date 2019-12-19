package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordAccountLoginRegisterEverydayDao;
import com.ozygod.model.zdlog.entity.TblRecordAccountLoginRegisterEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordAccountLoginRegisterEverydayListDto;
import com.ozygod.model.zdlog.service.TblRecordAccountLoginRegisterEverydayService;


@Service("tblRecordAccountLoginRegisterEverydayService")
public class TblRecordAccountLoginRegisterEverydayServiceImpl extends ServiceImpl<TblRecordAccountLoginRegisterEverydayDao, TblRecordAccountLoginRegisterEverydayEntity> implements TblRecordAccountLoginRegisterEverydayService {


    @Override
    public ResponseBO queryPage(TblRecordAccountLoginRegisterEverydayListDto tblRecordAccountLoginRegisterEveryday) {
        IPage<TblRecordAccountLoginRegisterEverydayEntity> page = baseMapper.selectPage(
                tblRecordAccountLoginRegisterEveryday.getPage(),
                new QueryWrapper<TblRecordAccountLoginRegisterEverydayEntity>()
        );
        return ResponseBO.page(page);
    }
}
