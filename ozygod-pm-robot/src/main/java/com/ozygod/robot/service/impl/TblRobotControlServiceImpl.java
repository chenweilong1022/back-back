package com.ozygod.robot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdconfig.dao.TblRobotControlDao;
import com.ozygod.model.zdconfig.dto.TblRobotControlListDto;
import com.ozygod.model.zdconfig.entity.TblRobotControlEntity;
import com.ozygod.robot.service.TblRobotControlService;
import org.springframework.stereotype.Service;


@Service("tblRobotControlService")
public class TblRobotControlServiceImpl extends ServiceImpl<TblRobotControlDao, TblRobotControlEntity> implements TblRobotControlService {

    @Override
    public ResponseBO queryPage(TblRobotControlListDto tblRobotControlListDto) {
        IPage<TblRobotControlEntity> page = baseMapper.selectPage(
                tblRobotControlListDto.getPage(),
                new QueryWrapper<TblRobotControlEntity>().lambda()
                .like(StrUtil.isNotBlank(tblRobotControlListDto.getName()),TblRobotControlEntity::getName,tblRobotControlListDto.getName())
        );
        return ResponseBO.page(page);
    }

}
