package com.ozygod.model.zdlog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdlog.dao.TblDailyAdventureAwardGetRecordDao;
import com.ozygod.model.zdlog.dto.TblDailyAdventureAwardGetRecordListDto;
import com.ozygod.model.zdlog.entity.TblDailyAdventureAwardGetRecordEntity;
import com.ozygod.model.zdlog.service.TblDailyAdventureAwardGetRecordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service("tblDailyAdventureAwardGetRecordService")
public class TblDailyAdventureAwardGetRecordServiceImpl extends ServiceImpl<TblDailyAdventureAwardGetRecordDao, TblDailyAdventureAwardGetRecordEntity> implements TblDailyAdventureAwardGetRecordService {


    @Override
    public ResponseBO queryPage(TblDailyAdventureAwardGetRecordListDto tblDailyAdventureAwardGetRecord) {
        IPage<TblDailyAdventureAwardGetRecordEntity> page = baseMapper.selectPage(
                tblDailyAdventureAwardGetRecord.getPage(),
                new QueryWrapper<TblDailyAdventureAwardGetRecordEntity>().lambda()
                .like(ObjectUtil.isNotNull(tblDailyAdventureAwardGetRecord.getUserid()),TblDailyAdventureAwardGetRecordEntity::getUserid,tblDailyAdventureAwardGetRecord.getUserid())
        );
        return ResponseBO.page(page);
    }


    @Override
    @Async
    public boolean saveBatch(Collection<TblDailyAdventureAwardGetRecordEntity> entityList) {
        return super.saveBatch(entityList);
    }
}
