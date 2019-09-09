package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.bo.RobotMenuBO;
import com.ozygod.model.zdmanage.dto.RobotMenuDto;
import com.ozygod.model.zdmanage.entity.RobotMenuEntity;

import java.util.List;

public interface RobotMenuEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RobotMenuEntity record);

    int insertSelective(RobotMenuEntity record);

    RobotMenuEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RobotMenuEntity record);

    int updateByPrimaryKey(RobotMenuEntity record);

    /**
     * 查询机器人菜单列表
     * @param dto
     * @return
     */
    List<RobotMenuBO> listRobotMenuByQry(RobotMenuDto dto);
}