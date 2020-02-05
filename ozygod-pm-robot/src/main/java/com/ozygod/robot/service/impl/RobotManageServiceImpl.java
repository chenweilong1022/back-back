package com.ozygod.robot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.HttpRequestUtil;
import com.ozygod.model.common.bo.RobotBO;
import com.ozygod.model.common.bo.RobotConfigBO;
import com.ozygod.model.common.dto.ChangeGameMoneyDto;
import com.ozygod.model.common.dto.RobotDto;
import com.ozygod.model.zdconfig.enums.GameConfig;
import com.ozygod.model.zdconfig.vo.game.BaseGameConfigVo;
import com.ozygod.model.zdconfig.vo.room.RoomConfigVo;
import com.ozygod.model.zdgame.dao.PoolEntityMapper;
import com.ozygod.model.zdmanage.bo.RobotMenuBO;
import com.ozygod.robot.service.IRobotManageService;
import com.ozygod.robot.service.IRobotMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/11
 */
@Slf4j
@Component
public class RobotManageServiceImpl implements IRobotManageService {

    @Autowired
    private PoolEntityMapper poolEntityMapper;

    @Value("${robot_url}")
    private String robotUrl;

    @Value("${game_url}")
    private String gameUrl;

    @Autowired
    private IRobotMenuService robotMenuService;


    /**
     * 查询所有机器人状态列表
     *
     * @return
     */
    @Override
    public List<RobotMenuBO> listRobotStatus() {
        List<RobotMenuBO> robotMenuBOS = robotMenuService.listAllRobotMenu();
        for (RobotMenuBO superBO :
                robotMenuBOS) {
            for (RobotMenuBO child :
                    superBO.getChildren()) {
                String roomId = superBO.getMenuNum() + child.getMenuNum();
                String result = HttpRequestUtil.sendGet(robotUrl + "/isAlive?roomid="+roomId);
                child.setStatus(result);
            }
        }
        return robotMenuBOS;
    }

    /**
     * 根据房间号查询机器人配置
     *
     * @param roomId
     * @return
     */
    @Override
    public RobotBO getRobotByRoomId(Integer roomId) {
        String result = HttpRequestUtil.sendGet(robotUrl + "/get?roomid=" + roomId);

        RobotBO robotBO = JSON.parseObject(result, RobotBO.class);
        if (robotBO != null) {
            //获取房间库存金币
            robotBO.setRoomMoney(poolEntityMapper.getPoolMoneyByRoomId(roomId));
        }
        return robotBO;
    }

    /**
     * 启动机器人
     *
     * @param roomId
     * @return
     */
    @Override
    public int startRobot(Integer roomId) {
        String result = HttpRequestUtil.sendGet(robotUrl + "/start?roomid=" + roomId);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 重启机器人
     *
     * @param roomId
     * @return
     */
    @Override
    public int restartRobot(Integer roomId) {
        String result = HttpRequestUtil.sendGet(robotUrl + "/restart?roomid=" + roomId);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 清空房间金币库存
     *
     * @param roomId
     * @param dto
     * @return
     */
    @Override
    public int changeRoomMoney(Integer roomId, ChangeGameMoneyDto dto) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/modify_pool?gameid="+ dto.getGameId()
                + "&roomid=" + roomId + "&tableid=" + dto.getTableId() + "&who=" + dto.getWho()
                + "&change="+ dto.getMoney());
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 关闭机器人
     *
     * @param roomId
     * @return
     */
    @Override
    public int stopRobot(Integer roomId) {
        String result = HttpRequestUtil.sendGet(robotUrl + "/stop?roomid=" + roomId);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 修改机器人信息
     *
     * @param roomId
     * @param robotBO
     * @return
     */
    @Override
    public int updateRobot(Integer roomId, RobotBO robotBO) {
        String value = JSON.toJSONString(robotBO);
        try {
            value = URLEncoder.encode(value, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        String result = HttpRequestUtil.sendGet(robotUrl + "/set?roomid=" + roomId + "&value=" + value);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询机器人列表
     *
     * @param dto
     * @return
     */
    @Override
    public Object getRobotsByQry(RobotDto dto) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/get_robots?gameid=" + Math.floor(dto.getRoomId() / 100));
        log.info("result:" + result);

        if (StrUtil.isNotBlank(result) && JSONUtil.isJson(result)) {
            Map<String, Object> resultMap = JSON.parseObject(result, new TypeReference<Map<String, Object>>(){});
            return resultMap.get(String.valueOf(dto.getRoomId()));
        }
        return null;
    }

    /**
     * 添加机器人
     *
     * @param bo
     * @return
     */
    @Override
    public int addRobot(RobotConfigBO bo) {
        log.info(gameUrl + "/add_robot?roomid=" + bo.getRoomId() + "&tableid=" + bo.getTableId());
        String result = HttpRequestUtil.sendGet(gameUrl + "/add_robot?roomid=" + bo.getRoomId() + "&tableid=" + bo.getTableId());
        log.info("result:" + result);
        if (CommonUtil.isInteger(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 重置机器人
     *
     * @param bo
     * @return
     */
    @Override
    public int resetRobot(RobotConfigBO bo) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/reset_robot?roomid=" + bo.getRoomId() + "&uid=" + bo.getUid());
        log.info("result:" + result);
        return Integer.parseInt(result);
    }

    @Override
    public int deleteRobot(RobotConfigBO bo) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/del_robot?roomid=" + bo.getRoomId() + "&tableid=" + bo.getTableId() + "&uid=" + bo.getUid());
        log.info("result:" + result);
        return Integer.parseInt(result);
    }


    /**
     * 获取机器人配置
     *
     * @param dto
     * @return
     */
    @Override
    public RoomConfigVo getRobotConfigByQry(RobotDto dto) {

        Integer roomId = dto.getRoomId();
        String result = HttpRequestUtil.sendGet(gameUrl + "/get_robot_config?roomid=" + roomId);
        Integer gameId = roomId / 100;

        GameConfig gameConfig = GameConfig.getByKey(gameId);

        RoomConfigVo roomConfigVo = new RoomConfigVo();
        roomConfigVo.setRoomId(roomId);
        roomConfigVo.setGameId(gameId);
        roomConfigVo.setGameName(gameConfig.getValue());
        roomConfigVo.setCardName(gameConfig.getCardName());

        if (StrUtil.isNotBlank(result) && JSONUtil.isJson(result)) {
            BaseGameConfigVo baseGameConfigVo = JSONUtil.toBean(result, gameConfig.getBaseGameConfigVo().getClass());
            roomConfigVo.setBaseGameConfigVo(baseGameConfigVo);
            return roomConfigVo;
        }
        return roomConfigVo;
    }

    /**
     * 设置机器人配置
     *
     * @param bo
     * @return
     */
    @Override
    public int updateRobotConfig(RobotConfigBO bo) {
        try {
            log.info(gameUrl + "/set_robot_config?roomid=" + bo.getRoomId() + "&config=" + URLEncoder.encode(bo.getConfig()));
            String result = HttpRequestUtil.sendGet(gameUrl + "/set_robot_config?roomid=" + bo.getRoomId() + "&config=" + URLEncoder.encode(bo.getConfig(), "utf-8"));
            log.info("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return 1;
    }
}
