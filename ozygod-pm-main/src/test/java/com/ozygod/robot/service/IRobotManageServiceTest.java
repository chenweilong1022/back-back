package com.ozygod.robot.service;

import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.utils.HttpRequestUtil;
import com.ozygod.main.OzygodPmMainApplication;
import com.ozygod.model.zdconfig.entity.TblGameRoomEntity;
import com.ozygod.model.zdconfig.service.impl.TblGameRoomServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 16:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
@Slf4j
public class IRobotManageServiceTest {

    @Value("${game_url}")
    private String gameUrl;
    @Value("${robot_url}")
    private String robotUrl;

    @Autowired
    private TblGameRoomServiceImpl tblGameRoomService;

    @Test
    public void config() {

        List<TblGameRoomEntity> list = tblGameRoomService.list(new QueryWrapper<TblGameRoomEntity>().lambda()
        );

        for (TblGameRoomEntity tblGameRoomEntity : list) {
            Map map = new HashMap();
            map.put("roomid",tblGameRoomEntity.getRoomid());
            try {
                String s = HttpUtil.get(gameUrl + "/get_robot_config", map,3000);
                log.info("roomName = {},config = {}",tblGameRoomEntity.getRoomName(),s);
            }catch (Exception e ) {
                log.info("roomName = {},config = {}",tblGameRoomEntity.getRoomName(),"");
                continue;
            }

        }

    }



    @Test
    public void status() {

        List<TblGameRoomEntity> list = tblGameRoomService.list(new QueryWrapper<TblGameRoomEntity>().lambda()
        );

        for (TblGameRoomEntity tblGameRoomEntity : list) {
            Map map = new HashMap();
//            map.put("roomid",tblGameRoomEntity.getRoomid());
//            String s = HttpUtil.get(robotUrl + "/isAlive", map);

            String result = HttpRequestUtil.sendGet(robotUrl + "/get?roomid=" + tblGameRoomEntity.getRoomid());

            log.info("roomName = {},config = {}",tblGameRoomEntity.getRoomName(),result);

        }



    }


    @Test
    public void robots() {

        List<TblGameRoomEntity> list = tblGameRoomService.list(new QueryWrapper<TblGameRoomEntity>().lambda()
        );

        for (TblGameRoomEntity tblGameRoomEntity : list) {
            Map map = new HashMap();
            map.put("roomid",tblGameRoomEntity.getRoomid());
            log.info(robotUrl + "/get");
            log.info(HttpUtil.toParams(map));
            String s = HttpUtil.get(robotUrl + "/get", map);
            log.info("roomName = {},config = {}",tblGameRoomEntity.getRoomName(),s);

        }



    }


}
