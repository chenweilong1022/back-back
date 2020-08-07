package com.ozygod.robot.web;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.ozygod.model.common.bo.RobotConfigBO;
import com.ozygod.model.common.dto.ChangeGameMoneyDto;
import com.ozygod.model.common.dto.RobotDto;
import com.ozygod.model.common.dto.WinRateControlDTO;
import com.ozygod.model.zdconfig.enums.GameConfig;
import com.ozygod.model.zdconfig.vo.game.BaseGameConfigVo;
import com.ozygod.robot.service.IRobotManageService;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.common.bo.RobotBO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/11
 */
@RequestMapping(value = "/robots")
@RestController
@Slf4j
@Api(value = "/robots", description = "机器人管理" )
public class RobotController {

    @Autowired
    private IRobotManageService robotManageService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取机器人状态列表
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listRobotStatus() {

        return new ResponseBO(robotManageService.listRobotStatus());
    }

    /**
     * 根据房间id查询机器人信息
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO getRobotByRoomId(@PathVariable Integer roomId) {

        return new ResponseBO(robotManageService.getRobotByRoomId(roomId));
    }

    /**
     * 修改机器人配置
     * @param roomId
     * @param robotBO
     * @return
     */
    @RequestMapping(value = "/{roomId}", method = RequestMethod.PUT, headers = Constant.API_VERSION_V1)
    public ResponseBO updateRobot(@PathVariable Integer roomId, @RequestBody RobotBO robotBO) {
        int result = robotManageService.updateRobot(roomId, robotBO);
        if (result ==  0) {
            return new ResponseBO(ResponseCode.U001.getCode(), ResponseCode.U001.getTitle());
        }
        return new ResponseBO("修改成功");
    }

    /**
     * 启动机器人
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/{roomId}/start", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO startRobot(@PathVariable Integer roomId) {
        int result = robotManageService.startRobot(roomId);
        if (result ==  0) {
            return new ResponseBO(ResponseCode.R001.getCode(), ResponseCode.R001.getTitle());
        }
        return new ResponseBO("发送启动指令成功");
    }

    /**
     * 重新启动机器人
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/{roomId}/restart", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO restartRobot(@PathVariable Integer roomId) {
        int result = robotManageService.restartRobot(roomId);
        if (result ==  0) {
            return new ResponseBO(ResponseCode.R002.getCode(), ResponseCode.R002.getTitle());
        }
        return new ResponseBO("发送重启指令成功");
    }

    /**
     * 关闭机器人
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/{roomId}/stop", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO stopRobot(@PathVariable Integer roomId) {
        int result = robotManageService.stopRobot(roomId);
        if (result ==  0) {
            return new ResponseBO(ResponseCode.T001.getCode(), ResponseCode.T001.getTitle());
        }
        return new ResponseBO("发送关闭指令成功");
    }

    /**
     * 清空游戏房间金币库存
     * @param roomId
     * @param dto
     * @return
     */
    @RequestMapping(value = "/{roomId}/money/clear", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO clearGameMoney(@PathVariable Integer roomId, @RequestBody ChangeGameMoneyDto dto) {
        int result = robotManageService.changeRoomMoney(roomId, dto);
        if (result == 0) {
            return new ResponseBO(ResponseCode.T002.getCode(), ResponseCode.T002.getTitle());
        }
        return new ResponseBO("清空成功");
    }

    /**
     * 根据房间id查询机器人列表
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V2)
    public ResponseBO getRobotsByQry(@PathVariable Integer roomId) {
        RobotDto robotDto = new RobotDto();
        robotDto.setRoomId(roomId);
        return new ResponseBO(robotManageService.getRobotsByQry(robotDto));
    }

    /**
     * 添加机器人
     * @param bo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = Constant.API_VERSION_V2)
    public ResponseBO addRobot(@RequestBody RobotConfigBO bo) {
        int result = robotManageService.addRobot(bo);
        if (result ==  0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "添加失败");
        }
        return new ResponseBO("添加成功");
    }

    /**
     * 删除机器人
     * @param bo
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, headers = Constant.API_VERSION_V2)
    public ResponseBO deleteRobot(@RequestBody RobotConfigBO bo) {
        int result = robotManageService.deleteRobot(bo);
        if (result ==  25) {
            return new ResponseBO(ResponseCode.I001.getCode(), "机器人正在游戏，重置失败");
        }
        if (result > 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "删除失败");
        }
        return new ResponseBO("删除成功");
    }

    /**
     * 重置机器人
     * @param bo
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.PUT, headers = Constant.API_VERSION_V2)
    public ResponseBO resetRobot(@RequestBody RobotConfigBO bo) {
        int result = robotManageService.resetRobot(bo);
        if (result ==  25) {
            return new ResponseBO(ResponseCode.I001.getCode(), "机器人正在游戏，重置失败");
        }
        return new ResponseBO("重置成功");
    }

    /**
     * 查询机器人配置
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/{roomId}/config", method = RequestMethod.GET, headers = Constant.API_VERSION_V2)
    public ResponseBO getRobotConfigByQry(@PathVariable Integer roomId) {
        RobotDto robotDto = new RobotDto();
        robotDto.setRoomId(roomId);
        String winRateControlJson = redisTemplate.opsForValue().get("winRateControl:" + roomId);
        WinRateControlDTO winRateControlDTO = new WinRateControlDTO();
        if (StrUtil.isNotBlank(winRateControlJson) && JSONUtil.isJson(winRateControlJson)) {
            winRateControlDTO = JSONUtil.toBean(winRateControlJson, WinRateControlDTO.class);
        }

        return new ResponseBO(robotManageService.getRobotConfigByQry(robotDto).setWinRateControlDTO(winRateControlDTO));
    }

    /**
     * 修改机器人配置
     * @param roomId
     * @param bo
     * @return
     */
    @RequestMapping(value = "/{roomId}/config", method = RequestMethod.PUT, headers = Constant.API_VERSION_V2)
    public ResponseBO updateRobotConfig(@PathVariable Integer roomId, @RequestBody RobotConfigBO bo) {
        bo.setRoomId(roomId);
        Integer gameId = roomId / 100;
        log.info(bo.getConfig());

        WinRateControlDTO winRateControlDTO = bo.getWinRateControlDTO();

        if (ObjectUtil.isNotNull(winRateControlDTO) && ObjectUtil.isNotNull(winRateControlDTO.getTimeInterval())) {
            redisTemplate.opsForValue().set("winRateControl:" + roomId, JSONUtil.toJsonStr(winRateControlDTO));
            DateTime dateTime = DateUtil.offsetSecond(DateUtil.date(), winRateControlDTO.getTimeInterval().intValue());
            long ex = dateTime.getTime() - System.currentTimeMillis();
            redisTemplate.opsForValue().set("winRateControlRandom:" + roomId,"1",ex, TimeUnit.MILLISECONDS);
        }

        GameConfig gameConfig = GameConfig.getByKey(gameId);
        if (StrUtil.isNotEmpty(bo.getConfig())) {
            /**
             * 解决integer 转换成str问题
             */
            BaseGameConfigVo baseGameConfigVo = JSONUtil.toBean(bo.getConfig(), gameConfig.getBaseGameConfigVo().getClass());
            bo.setConfig(JSONUtil.toJsonStr(baseGameConfigVo));

            int result = robotManageService.updateRobotConfig(bo);
            if (result ==  0) {
                return new ResponseBO(ResponseCode.U001.getCode(), "修改失败");
            }
        }
        return new ResponseBO("修改成功");
    }
}
