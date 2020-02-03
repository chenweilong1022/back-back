package com.ozygod.model.zdconfig.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdconfig.dao.TblGameDao;
import com.ozygod.model.zdconfig.entity.TblGameEntity;
import com.ozygod.model.zdconfig.dto.TblGameListDto;
import com.ozygod.model.zdconfig.service.TblGameService;


@Service("tblGameService")
public class TblGameServiceImpl extends ServiceImpl<TblGameDao, TblGameEntity> implements TblGameService {


    @Override
    public ResponseBO queryPage(TblGameListDto tblGame) {
        IPage<TblGameEntity> page = baseMapper.selectPage(
                tblGame.getPage(),
                new QueryWrapper<TblGameEntity>()
        );
        return ResponseBO.page(page);
    }
}
