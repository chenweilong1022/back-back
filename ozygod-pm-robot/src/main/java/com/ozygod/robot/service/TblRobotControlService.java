package com.ozygod.robot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdconfig.dto.TblRobotControlListDto;
import com.ozygod.model.zdconfig.entity.TblRobotControlEntity;

/**
 * 机器人控制
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-12 17:06:41
 */
public interface TblRobotControlService extends IService<TblRobotControlEntity> {

    ResponseBO queryPage(TblRobotControlListDto tblRobotControlListDto);
}

