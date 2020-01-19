package com.ozygod.model.zdgame.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ozygod.base.enums.Global;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdgame.dao.TblAccountDao;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.dto.TblAccountListDto;
import com.ozygod.model.zdgame.service.TblAccountService;

import javax.xml.bind.annotation.XmlElementDecl;


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
//                .notIn(TblAccountEntity::getLoginTime, "1970-01-01 00:00:00")
                .isNotNull(TblAccountEntity::getLoginTime)
                .last(" and logout_time < login_time")
                .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
        );
        return onlineNumber;
    }

    @Override
    public int loginNumber(DateTime begin, DateTime end, DateTime oldTime, List<Long> userIds) {
        int count = this.count(new QueryWrapper<TblAccountEntity>().lambda()
                .ge(ObjectUtil.isNotNull(begin),TblAccountEntity::getLoginTime, begin)
                .le(ObjectUtil.isNotNull(end),TblAccountEntity::getLoginTime, end)
                .lt(ObjectUtil.isNotNull(oldTime),TblAccountEntity::getCreateTime, oldTime)
                .in(CollUtil.isNotEmpty(userIds),TblAccountEntity::getUserid,userIds)
                .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
        );
        return count;
    }

    @Override
    public int registerNumber(DateTime begin, DateTime end, List<Long> userIds) {
        int count = this.count(
                queryWrapper(begin,end,userIds)
        );
        return count;
    }

    @Override
    public List<TblAccountEntity> registerList(DateTime begin, DateTime end, List<Long> userIds) {
        List<TblAccountEntity> list = this.list(
                queryWrapper(begin,end,userIds)
        );
        return list;
    }


    private LambdaQueryWrapper queryWrapper(DateTime begin, DateTime end, List<Long> userIds) {
        LambdaQueryWrapper<TblAccountEntity> gt = new QueryWrapper<TblAccountEntity>().lambda()
                .ge(ObjectUtil.isNotNull(begin), TblAccountEntity::getCreateTime, begin)
                .le(ObjectUtil.isNotNull(end), TblAccountEntity::getCreateTime, end)
                .in(CollUtil.isNotEmpty(userIds), TblAccountEntity::getUserid, userIds)
                .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID);
        return gt;
    }
}
