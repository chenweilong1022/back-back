package com.ozygod.model.zdmanage.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.enums.WithdrawOrderState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<TblWithdrawOrderEntity> haveWithdrawal(DateTime begin, DateTime end,List<Long> userIds) {
        /**
         * 根据时间时间段查询已经提现的
         */
        List<TblWithdrawOrderEntity> tblWithdrawOrderEntitys = this.list(new QueryWrapper<TblWithdrawOrderEntity>().lambda()
                .ge(ObjectUtil.isNotNull(begin),TblWithdrawOrderEntity::getCompletetime, begin)
                .le(ObjectUtil.isNotNull(end),TblWithdrawOrderEntity::getCompletetime, end)
                .eq(TblWithdrawOrderEntity::getState, WithdrawOrderState.TWO.getKey())
                .in(CollUtil.isNotEmpty(userIds),TblWithdrawOrderEntity::getUserid,userIds)
        );
        return tblWithdrawOrderEntitys;
    }

    @Override
    public int totalBack(List<TblWithdrawOrderEntity> tblWithdrawOrderEntitys) {
        /**
         * 总提现
         */
        int totalBack = 0;
        if (CollUtil.isNotEmpty(tblWithdrawOrderEntitys)) {
            totalBack = tblWithdrawOrderEntitys.stream().mapToInt(TblWithdrawOrderEntity::getAmount).sum();
        }
        return totalBack;
    }

    @Override
    public int totalBackNumber(List<TblWithdrawOrderEntity> tblWithdrawOrderEntitys) {
        /**
         * 总体现人数
         */
        int totalBackNumber = 0;
        if (CollUtil.isNotEmpty(tblWithdrawOrderEntitys)) {
            totalBackNumber = tblWithdrawOrderEntitys.stream().map(TblWithdrawOrderEntity::getUserid).distinct().collect(Collectors.toList()).size();
        }
        return totalBackNumber;
    }


}
