package com.ozygod.model.zdgame.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdgame.dao.TblPlayerinfoDao;
import com.ozygod.model.zdgame.entity.TblPlayerinfoEntity;
import com.ozygod.model.zdgame.dto.TblPlayerinfoListDto;
import com.ozygod.model.zdgame.service.TblPlayerinfoService;


@Service("tblPlayerinfoService")
public class TblPlayerinfoServiceImpl extends ServiceImpl<TblPlayerinfoDao, TblPlayerinfoEntity> implements TblPlayerinfoService {


    @Override
    public ResponseBO queryPage(TblPlayerinfoListDto tblPlayerinfo) {
        IPage<TblPlayerinfoEntity> page = baseMapper.selectPage(
                tblPlayerinfo.getPage(),
                new QueryWrapper<TblPlayerinfoEntity>()
        );
        return ResponseBO.page(page);
    }
}
