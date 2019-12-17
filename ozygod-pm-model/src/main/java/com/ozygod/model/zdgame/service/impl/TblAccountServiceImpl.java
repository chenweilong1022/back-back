package com.ozygod.model.zdgame.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdgame.dao.TblAccountDao;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.dto.TblAccountListDto;
import com.ozygod.model.zdgame.service.TblAccountService;


@Service("tblAccountService")
public class TblAccountServiceImpl extends ServiceImpl<TblAccountDao, TblAccountEntity> implements TblAccountService {


    @Override
    public ResponseBO queryPage(TblAccountListDto tblAccount) {
        IPage<TblAccountEntity> page = baseMapper.selectPage(
                tblAccount.getPage(),
                new QueryWrapper<TblAccountEntity>()
        );
        return ResponseBO.page(page);
    }

    @Override
    public int onlineNumber() {
        /**
         * 在线数
         */
        int onlineNumber = this.count(new QueryWrapper<TblAccountEntity>().lambda()
                .notIn(TblAccountEntity::getLogoutTime, "1970-01-01 00:00:00")
                .last(" and logout_time < login_time")
        );
        return onlineNumber;
    }
}
