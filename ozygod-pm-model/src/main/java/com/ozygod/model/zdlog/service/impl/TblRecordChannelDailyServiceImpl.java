package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordChannelDailyDao;
import com.ozygod.model.zdlog.entity.TblRecordChannelDailyEntity;
import com.ozygod.model.zdlog.dto.TblRecordChannelDailyListDto;
import com.ozygod.model.zdlog.service.TblRecordChannelDailyService;


@Service("tblRecordChannelDailyService")
public class TblRecordChannelDailyServiceImpl extends ServiceImpl<TblRecordChannelDailyDao, TblRecordChannelDailyEntity> implements TblRecordChannelDailyService {


    @Override
    public ResponseBO queryPage(TblRecordChannelDailyListDto tblRecordChannelDaily) {
        IPage<TblRecordChannelDailyEntity> page = baseMapper.selectPage(
                tblRecordChannelDaily.getPage(),
                new QueryWrapper<TblRecordChannelDailyEntity>()
        );
        return ResponseBO.page(page);
    }
}
