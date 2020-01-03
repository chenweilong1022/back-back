package com.ozygod.model.zdgame.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<TblOrderEntity> tblOrderEntities = rechargeOrderList(begin,end,userIds);
        return recharge(tblOrderEntities);
    }

    @Override
    public int recharge(List<TblOrderEntity> tblOrderEntities) {
        /**
         * 充值金额
         */
        int sum = 0;
        if (CollUtil.isNotEmpty(tblOrderEntities)) {
            sum = tblOrderEntities.stream().mapToInt(TblOrderEntity::getMoney).sum();
        }
        return sum;
    }

    @Override
    public int rechargeUserCount(DateTime begin, DateTime end, List<Long> userIds) {
        List<TblOrderEntity> tblOrderEntities = rechargeOrderList(begin,end,userIds);
        return rechargeUserCount(tblOrderEntities);
    }

    @Override
    public int rechargeUserCount(List<TblOrderEntity> tblOrderEntities) {
        int count = CollUtil.isNotEmpty(tblOrderEntities) ? tblOrderEntities.stream().map(TblOrderEntity::getUserid).distinct().collect(Collectors.toList()).size() : 0;
        return count;
    }

    @Override
    public int rechargeOrderCount(DateTime begin, DateTime end, List<Long> userIds) {
        List<TblOrderEntity> tblOrderEntities = rechargeOrderList(begin,end,userIds);
        return rechargeOrderCount(tblOrderEntities);
    }

    @Override
    public int rechargeOrderCount(List<TblOrderEntity> tblOrderEntities) {
        int count = CollUtil.isNotEmpty(tblOrderEntities) ? tblOrderEntities.size() : 0;
        return count;
    }

    @Override
    public List<TblOrderEntity> rechargeOrderList(DateTime begin, DateTime end, List<Long> userIds) {
        /**
         * 根据条件查询订单
         */
        List<TblOrderEntity> list = this.list(new QueryWrapper<TblOrderEntity>().lambda()
                .ge(ObjectUtil.isNotNull(begin),TblOrderEntity::getPayTime, begin)
                .le(ObjectUtil.isNotNull(end),TblOrderEntity::getPayTime, end)
                .in(CollUtil.isNotEmpty(userIds),TblOrderEntity::getUserid,userIds)
        );
        return list;
    }


}
