package com.ozygod.game.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.game.service.TblPlayerLocationService;
import com.ozygod.model.zdgame.dao.TblPlayerLocationDao;
import com.ozygod.model.zdgame.entity.TblPlayerLocationEntity;
import org.springframework.stereotype.Service;


@Service("tblPlayerLocationService")
public class TblPlayerLocationServiceImpl extends ServiceImpl<TblPlayerLocationDao, TblPlayerLocationEntity> implements TblPlayerLocationService {

//    @Override
//    public PageUtils queryPage(TblPlayerLocationEntity tblPlayerLocation) {
//        IPage<TblPlayerLocationEntity> page = baseMapper.selectPage(
//                new Query<TblPlayerLocationEntity>(tblPlayerLocation).getPage(),
//                new QueryWrapper<TblPlayerLocationEntity>()
//        );
//
//        return new PageUtils(page);
//    }

}
