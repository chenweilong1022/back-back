package com.ozygod.model.zdlog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.GameGoldReason;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.model.zdlog.dao.TblGameGoldDao;
import com.ozygod.model.zdlog.dto.TblGameGoldListDto;
import com.ozygod.model.zdlog.entity.TblGameGoldEntity;
import com.ozygod.model.zdlog.service.TblGameGoldService;
import com.ozygod.model.zdlog.vo.TblGameGoldEntityPageVo;
import com.ozygod.model.zdlog.vo.TblGameGoldEntityTotalVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("tblGameGoldService")
public class TblGameGoldServiceImpl extends ServiceImpl<TblGameGoldDao, TblGameGoldEntity> implements TblGameGoldService {


    @Override
    public ResponseBO queryPage(TblGameGoldListDto tblGameGold) {

        String value = EnumUtil.queryValueByKey(tblGameGold.getGameGoldReason(), GameGoldReason.values());

        List<TblGameGoldEntity> tblGameGoldEntities = baseMapper.selectList(new QueryWrapper<TblGameGoldEntity>().lambda()
                .eq(StrUtil.isNotBlank(value), TblGameGoldEntity::getReason, value)
        );


        /**
         * 分页
         */
        Page page = tblGameGold.getPage();
        page.setTotal(tblGameGoldEntities.size());
        List<TblGameGoldEntity> records = tblGameGoldEntities.stream().skip((page.getCurrent() - 1) * page.getSize()).limit(page.getSize()).collect(Collectors.toList());


        long changeGold = 0L;

        if (CollUtil.isNotEmpty(records)) {
            changeGold = records.stream().mapToLong(TblGameGoldEntity::getChangeGold).sum();
        }


        /**
         * 合计金币
         */
        TblGameGoldEntityTotalVo tblGameGoldEntityTotalVo = new TblGameGoldEntityTotalVo();
        tblGameGoldEntityTotalVo.setChangeGold(changeGold);

        /**
         * 返回vo
         */
        TblGameGoldEntityPageVo tblGameGoldEntityPageVo = new TblGameGoldEntityPageVo();
        tblGameGoldEntityPageVo.setTblGameGoldEntities(records);
        tblGameGoldEntityPageVo.setTblGameGoldEntityTotalVo(tblGameGoldEntityTotalVo);


        return ResponseBO.page(page).setData(tblGameGoldEntityPageVo);
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

