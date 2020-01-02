package com.ozygod.model.zdlog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdlog.dao.TblRecordChannelDailyDao;
import com.ozygod.model.zdlog.dto.TblRecordChannelDailyListDto;
import com.ozygod.model.zdlog.entity.TblRecordChannelDailyEntity;
import com.ozygod.model.zdlog.service.TblRecordChannelDailyService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("tblRecordChannelDailyService")
public class TblRecordChannelDailyServiceImpl extends ServiceImpl<TblRecordChannelDailyDao, TblRecordChannelDailyEntity> implements TblRecordChannelDailyService {


    @Override
    public ResponseBO queryPage(TblRecordChannelDailyListDto tblRecordChannelDaily) {
        IPage<TblRecordChannelDailyEntity> page = baseMapper.selectPage(
                tblRecordChannelDaily.getPage(),
                new QueryWrapper<TblRecordChannelDailyEntity>().lambda()
                .eq(ObjectUtil.isNotNull(tblRecordChannelDaily.getCurrentDates()),TblRecordChannelDailyEntity::getCurrentDates,tblRecordChannelDaily.getCurrentDates())
        );

        List<TblRecordChannelDailyEntity> records = page.getRecords();

        TblRecordChannelDailyEntity totalTblRecordChannelDailyEntity = new TblRecordChannelDailyEntity();
        totalTblRecordChannelDailyEntity.setGold(records.stream().mapToLong(TblRecordChannelDailyEntity::getGold).sum());
        totalTblRecordChannelDailyEntity.setBankGold(records.stream().mapToLong(TblRecordChannelDailyEntity::getBankGold).sum());
        totalTblRecordChannelDailyEntity.setTotalGold(records.stream().mapToLong(TblRecordChannelDailyEntity::getTotalGold).sum());
        totalTblRecordChannelDailyEntity.setIsDel(records.stream().mapToInt(TblRecordChannelDailyEntity::getIsDel).sum());
        totalTblRecordChannelDailyEntity.setProfit(records.stream().mapToInt(TblRecordChannelDailyEntity::getProfit).sum());
        totalTblRecordChannelDailyEntity.setRecharge(records.stream().mapToInt(TblRecordChannelDailyEntity::getRecharge).sum());
        totalTblRecordChannelDailyEntity.setConversion(records.stream().mapToInt(TblRecordChannelDailyEntity::getConversion).sum());
        totalTblRecordChannelDailyEntity.setRegisterUsers(records.stream().mapToInt(TblRecordChannelDailyEntity::getRegisterUsers).sum());
        totalTblRecordChannelDailyEntity.setLoginUsers(records.stream().mapToInt(TblRecordChannelDailyEntity::getLoginUsers).sum());
        totalTblRecordChannelDailyEntity.setTotalRevenue(records.stream().mapToLong(TblRecordChannelDailyEntity::getTotalRevenue).sum());
        totalTblRecordChannelDailyEntity.setGameRecord(records.stream().mapToInt(TblRecordChannelDailyEntity::getGameRecord).sum());

        records.add(totalTblRecordChannelDailyEntity);
        return ResponseBO.page(page);
    }
}
