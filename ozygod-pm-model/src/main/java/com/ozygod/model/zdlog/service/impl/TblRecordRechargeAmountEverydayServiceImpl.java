package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordRechargeAmountEverydayDao;
import com.ozygod.model.zdlog.entity.TblRecordRechargeAmountEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordRechargeAmountEverydayListDto;
import com.ozygod.model.zdlog.service.TblRecordRechargeAmountEverydayService;


@Service("tblRecordRechargeAmountEverydayService")
public class TblRecordRechargeAmountEverydayServiceImpl extends ServiceImpl<TblRecordRechargeAmountEverydayDao, TblRecordRechargeAmountEverydayEntity> implements TblRecordRechargeAmountEverydayService {


    @Override
    public ResponseBO queryPage(TblRecordRechargeAmountEverydayListDto tblRecordRechargeAmountEveryday) {
        IPage<TblRecordRechargeAmountEverydayEntity> page = baseMapper.selectPage(
                tblRecordRechargeAmountEveryday.getPage(),
                new QueryWrapper<TblRecordRechargeAmountEverydayEntity>()
        );
        return ResponseBO.page(page);
    }
}
