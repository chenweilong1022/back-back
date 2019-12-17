package com.ozygod.model.zdgame.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdgame.dao.TblOrderDao;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.dto.TblOrderListDto;
import com.ozygod.model.zdgame.service.TblOrderService;


@Service("tblOrderService")
public class TblOrderServiceImpl extends ServiceImpl<TblOrderDao, TblOrderEntity> implements TblOrderService {


    @Override
    public ResponseBO queryPage(TblOrderListDto tblOrder) {
        IPage<TblOrderEntity> page = baseMapper.selectPage(
                tblOrder.getPage(),
                new QueryWrapper<TblOrderEntity>()
        );
        return ResponseBO.page(page);
    }
}
