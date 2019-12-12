package com.ozygod.robot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.model.zdconfig.dao.TblRobotControlDao;
import com.ozygod.model.zdconfig.entity.TblRobotControlEntity;
import com.ozygod.robot.service.TblRobotControlService;
import org.springframework.stereotype.Service;


@Service("tblRobotControlService")
public class TblRobotControlServiceImpl extends ServiceImpl<TblRobotControlDao, TblRobotControlEntity> implements TblRobotControlService {

//    @Override
//    public PageUtils queryPage(TblRobotControlEntity tblRobotControl) {
//        IPage<TblRobotControlEntity> page = baseMapper.selectPage(
//                new Query<TblRobotControlEntity>(tblRobotControl).getPage(),
//                new QueryWrapper<TblRobotControlEntity>()
//        );
//
//        return new PageUtils(page);
//    }

}
