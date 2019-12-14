package com.ozygod.game.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.game.service.TblAccountService;
import com.ozygod.model.zdgame.dao.TblAccountDao;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import org.springframework.stereotype.Service;


@Service("tblAccountService")
public class TblAccountServiceImpl extends ServiceImpl<TblAccountDao, TblAccountEntity> implements TblAccountService {

//    @Override
//    public PageUtils queryPage(TblAccountEntity tblAccount) {
//        IPage<TblAccountEntity> page = baseMapper.selectPage(
//                new Query<TblAccountEntity>(tblAccount).getPage(),
//                new QueryWrapper<TblAccountEntity>()
//        );
//
//        return new PageUtils(page);
//    }

}
