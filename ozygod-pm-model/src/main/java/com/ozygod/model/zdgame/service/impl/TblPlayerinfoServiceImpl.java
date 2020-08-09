package com.ozygod.model.zdgame.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.model.zdgame.vo.AgentTreeVo;
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
            agentTreeVos.forEach(this::sub);
        }
    }
}
