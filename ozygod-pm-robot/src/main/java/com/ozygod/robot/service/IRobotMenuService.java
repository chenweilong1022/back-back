package com.ozygod.robot.service;

import com.ozygod.model.zdmanage.bo.RobotMenuBO;
import com.ozygod.model.zdmanage.dto.RobotMenuDto;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
public interface IRobotMenuService {
    /**
     * 新增机器人菜单
     * @param bo
     * @return
     */
    int saveRobotMenu(RobotMenuBO bo) throws Exception;

    /**
     * 查询出全部机器人菜单
     * @return
     */
    List<RobotMenuBO> listAllRobotMenu();

    /**
     * 查询指定级别的菜单列表
     * @param dto
     * @return
     */
    List<RobotMenuBO> listRobotMenuByQry(RobotMenuDto dto);

    /**
     * 更新机器人菜单信息
     * @param bo
     * @return
     */
    int updateRobotMenu(RobotMenuBO bo) throws Exception;
}
