package com.ozygod.model.zdlog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordChannelGeneralizeDao;
import com.ozygod.model.zdlog.entity.TblRecordChannelGeneralizeEntity;
import com.ozygod.model.zdlog.dto.TblRecordChannelGeneralizeListDto;
import com.ozygod.model.zdlog.service.TblRecordChannelGeneralizeService;


@Service("tblRecordChannelGeneralizeService")
public class TblRecordChannelGeneralizeServiceImpl extends ServiceImpl<TblRecordChannelGeneralizeDao, TblRecordChannelGeneralizeEntity> implements TblRecordChannelGeneralizeService {


    @Override
    public ResponseBO queryPage(TblRecordChannelGeneralizeListDto tblRecordChannelGeneralize) {
        IPage<TblRecordChannelGeneralizeEntity> page = baseMapper.selectPage(
                tblRecordChannelGeneralize.getPage(),
                new QueryWrapper<TblRecordChannelGeneralizeEntity>()
        );
        return ResponseBO.page(page);
    }
}
