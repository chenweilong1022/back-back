package com.ozygod.model.zdlog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblGameGoldDao;
import com.ozygod.model.zdlog.entity.TblGameGoldEntity;
import com.ozygod.model.zdlog.dto.TblGameGoldListDto;
import com.ozygod.model.zdlog.service.TblGameGoldService;


@Service("tblGameGoldService")
public class TblGameGoldServiceImpl extends ServiceImpl<TblGameGoldDao, TblGameGoldEntity> implements TblGameGoldService {


    @Override
    public ResponseBO queryPage(TblGameGoldListDto tblGameGold) {
        IPage<TblGameGoldEntity> page = baseMapper.selectPage(
                tblGameGold.getPage(),
                new QueryWrapper<TblGameGoldEntity>()
        );
        return ResponseBO.page(page);
    }


    @Override
    public List<TblGameGoldEntity> tblGameGoldEntities(DateTime begin, DateTime end, List<Long> userIds) {
        List<TblGameGoldEntity> tblGameGoldEntities = this.list(new QueryWrapper<TblGameGoldEntity>().lambda()
                .ge(TblGameGoldEntity::getRecordTime, begin)
                .le(TblGameGoldEntity::getRecordTime, end)
                .in(TblGameGoldEntity::getUserid, userIds)
        );
        return tblGameGoldEntities;
    }

    @Override
    public long totalRevenue(List<TblGameGoldEntity> tblGameGoldEntities) {
        long totalRevenue = 0;
        if (CollUtil.isNotEmpty(tblGameGoldEntities)) {
            totalRevenue = tblGameGoldEntities.stream().mapToLong(TblGameGoldEntity::getTaxGold).sum();
        }
        return totalRevenue;
    }
}

