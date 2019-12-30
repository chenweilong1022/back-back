package com.ozygod.model.zdgame.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;

import java.util.List;
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


    @Override
    public int orderCount(DateTime begin, DateTime end, DateTime payTime) {
        int count = this.count(new QueryWrapper<TblOrderEntity>().lambda()
                .ge(ObjectUtil.isNotNull(begin),TblOrderEntity::getCreateTime, begin)
                .le(ObjectUtil.isNotNull(end),TblOrderEntity::getCreateTime, end)
                .ge(ObjectUtil.isNotNull(payTime),TblOrderEntity::getPayTime,payTime)
        );
        return count;
    }


    @Override
    public int recharge(DateTime begin, DateTime end, List<Long> userIds) {
        /**
         * 充值金额
         */
        List<TblOrderEntity> list = this.list(new QueryWrapper<TblOrderEntity>().lambda()
                .ge(ObjectUtil.isNotNull(begin),TblOrderEntity::getPayTime, begin)
                .le(ObjectUtil.isNotNull(end),TblOrderEntity::getPayTime, end)
                .in(CollUtil.isNotEmpty(userIds),TblOrderEntity::getUserid,userIds)
        );

        int sum = 0;
        if (CollUtil.isNotEmpty(list)) {
            sum = list.stream().mapToInt(TblOrderEntity::getMoney).sum();
        }

        return sum;
    }


}
