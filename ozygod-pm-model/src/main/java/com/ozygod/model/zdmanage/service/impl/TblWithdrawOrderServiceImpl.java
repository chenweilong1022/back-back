package com.ozygod.model.zdmanage.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdmanage.dao.TblWithdrawOrderDao;
import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.dto.TblWithdrawOrderListDto;
import com.ozygod.model.zdmanage.service.TblWithdrawOrderService;


@Service("tblWithdrawOrderService")
public class TblWithdrawOrderServiceImpl extends ServiceImpl<TblWithdrawOrderDao, TblWithdrawOrderEntity> implements TblWithdrawOrderService {


    @Override
    public ResponseBO queryPage(TblWithdrawOrderListDto tblWithdrawOrder) {
        IPage<TblWithdrawOrderEntity> page = baseMapper.selectPage(
                tblWithdrawOrder.getPage(),
                new QueryWrapper<TblWithdrawOrderEntity>()
        );
        return ResponseBO.page(page);
    }
}
