package com.ozygod.robot.service;

import com.ozygod.model.common.bo.RobotBO;
import com.ozygod.model.common.bo.RobotConfigBO;
import com.ozygod.model.common.dto.ChangeGameMoneyDto;
import com.ozygod.model.common.dto.RobotDto;
import com.ozygod.model.zdconfig.vo.room.RoomConfigVo;
import com.ozygod.model.zdmanage.bo.RobotMenuBO;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/11
 */
public interface IRobotManageService {

    /**
     * 查询所有机器人状态列表
     * @return
     */
    List<RobotMenuBO> listRobotStatus();

    /**
     * 根据房间号查询机器人配置
     * @param roomId
     * @return
     */
    RobotBO getRobotByRoomId(Integer roomId);

    /**
     * 启动机器人
     * @param roomId
     * @return
     */
    int startRobot(Integer roomId);

    /**
     * 修改机器人信息
     * @param roomId
     * @param robotBO
     * @return
     */
    int updateRobot(Integer roomId, RobotBO robotBO);

    /**
     * 重启机器人
     * @param roomId
     * @return
     */
    int restartRobot(Integer roomId);

    /**
     * 关闭机器人
     * @param roomId
     * @return
     */
    int stopRobot(Integer roomId);

    /**
     * 清空房间金币库存
     * @param roomId
     * @param dto
     * @return
     */
    int changeRoomMoney(Integer roomId, ChangeGameMoneyDto dto);

    /**
     * 查询机器人列表
     * @param dto
     * @return
     */
    Object getRobotsByQry(RobotDto dto);

    /**
     * 添加机器人
     * @param bo
     * @return
     */
    int addRobot(RobotConfigBO bo);

    /**
     * 重置机器人
     * @param bo
     * @return
     */
    int resetRobot(RobotConfigBO bo);

    int deleteRobot(RobotConfigBO bo);

    /**
     * 获取机器人配置
     * @param dto
     * @return
     */
    RoomConfigVo getRobotConfigByQry(RobotDto dto);

    /**
     * 设置机器人配置
     * @param bo
     * @return
     */
    int updateRobotConfig(RobotConfigBO bo);
}
