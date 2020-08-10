package com.ozygod.model.zdgame.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdgame.vo.AgentTreeVo;
import com.ozygod.model.zdmanage.dao.TblWithdrawOrderDao;
import com.ozygod.model.zdmanage.dao.WithdrawOrderEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private TblOrderService tblOrderService;
    @Autowired
    private WithdrawOrderEntityMapper withdrawOrderEntityMapper;

    @Override
    public ResponseBO queryPage(TblPlayerinfoListDto tblPlayerinfo) {
        IPage<TblPlayerinfoEntity> page = baseMapper.selectPage(
                tblPlayerinfo.getPage(),
                new QueryWrapper<TblPlayerinfoEntity>()
        );
        return ResponseBO.page(page);
    }

    @Override
    public List<AgentTreeVo> agentTree(Long saler,Long userid) {
        List<AgentTreeVo> agentTreeVos = baseMapper.agentTree(saler,userid);
        agentTreeVos.forEach(this::sub);
        return agentTreeVos;
    }

    private void sub(AgentTreeVo agentTreeVo) {
        List<AgentTreeVo> agentTreeVos = baseMapper.agentTree(agentTreeVo.getShowId(),null);
        if (CollUtil.isNotEmpty(agentTreeVos)) {
            agentTreeVo.setChildrens(agentTreeVos);
            Integer rechargePrice = tblOrderService.rechargePrice(agentTreeVo.getUserid());
            agentTreeVo.setRechargePrice(rechargePrice);

            Integer withdrawAmount = withdrawOrderEntityMapper.withdrawAmount(agentTreeVo.getUserid());
            agentTreeVo.setWithdrawAmount(withdrawAmount);

            agentTreeVos.forEach(this::sub);
        }
    }
}
