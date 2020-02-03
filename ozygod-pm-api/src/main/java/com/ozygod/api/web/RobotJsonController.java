package com.ozygod.api.web;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.PinyinUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.model.zdconfig.entity.TblGameEntity;
import com.ozygod.model.zdconfig.entity.TblGameRoomEntity;
import com.ozygod.model.zdconfig.service.TblGameRoomService;
import com.ozygod.model.zdconfig.service.TblGameService;
import com.ozygod.model.zdconfig.vo.MenuMetaVo;
import com.ozygod.model.zdconfig.vo.MenuVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 11:21
 */
@RequestMapping(value = "/api/robotsjson")
@RestController
@Slf4j
@Api(value = "api/robotsjson", description = "机器人管理" )
public class RobotJsonController {


//    {
//    path: '/platformRobotManagement',
//    redirect: 'noredirect',
//    component: Layout,
//    name: 'platformRobotManagement',
//    alwaysShow: true,
//    meta: {
//      title: '平台机器人管理',
//      path: '/platformRobotManagement',
//      auth: true,
//      icon: 'nested'
//    },
//    children: [
//      {
//        path: 'robotManage',
//        name: 'RobotManage',
//        component: () => import('@/views/platform/robotManage'),
//        meta: {
//          title: '机器人管理',
//          path: '/player/robotManage',
//          auth: true
//        }
//      },
//    ]
//  },


    @Autowired
    private TblGameService tblGameService;
    @Autowired
    private TblGameRoomService tblGameRoomService;

    @RequestMapping(value = "/getJson", method = RequestMethod.GET)
    public MenuVo getJson() {
        MenuMetaVo menuMetaVo = new MenuMetaVo();
        menuMetaVo.setTitle("平台机器人管理");
        menuMetaVo.setPath("robots");
        menuMetaVo.setAuth(true);
        menuMetaVo.setIcon("nested");

        MenuVo menuVo = new MenuVo();
        menuVo.setPath("robots");
        menuVo.setRedirect("noredirect");
        menuVo.setComponent("Layout");
        menuVo.setName("robots");
        menuVo.setAlwaysShow(true);
        menuVo.setMeta(menuMetaVo);


        MenuMetaVo menuMetaVoConfig = new MenuMetaVo();
        menuMetaVoConfig.setTitle("机器人配置");
        menuMetaVoConfig.setPath("'config'");
        menuMetaVoConfig.setAuth(true);

        MenuVo menuVoConfig = new MenuVo();
        menuVoConfig.setPath("config");
        menuVoConfig.setComponent("() => import('@/views/robotManager/config')");
        menuVoConfig.setName("config");
        menuVoConfig.setMeta(menuMetaVoConfig);

        menuVo.add(menuVoConfig);



        List<TblGameEntity> list = tblGameService.list(null);


        for (TblGameEntity tblGameEntity : list) {
            String gameName = tblGameEntity.getGameName();
            String gameNamePinYin = PinyinUtil.getPinYin(gameName);

            MenuMetaVo menuMetaVoOne = new MenuMetaVo();
            menuMetaVoOne.setTitle(gameName);
            menuMetaVoOne.setPath(gameNamePinYin);
            menuMetaVoOne.setAuth(true);
            menuMetaVoOne.setIcon("nested");

            MenuVo menuVoOne = new MenuVo();
            menuVoOne.setPath(gameNamePinYin);
            menuVoOne.setRedirect("noredirect");
            menuVoOne.setComponent("Layout");
            menuVoOne.setName(gameNamePinYin);
            menuVoOne.setAlwaysShow(true);
            menuVoOne.setMeta(menuMetaVoOne);

            List<TblGameRoomEntity> tblGameRoomEntities = tblGameRoomService.list(new QueryWrapper<TblGameRoomEntity>().lambda()
                    .eq(TblGameRoomEntity::getGameid,tblGameEntity.getGameid())
            );

            if (CollUtil.isNotEmpty(tblGameRoomEntities)) {

                for (TblGameRoomEntity tblGameRoomEntity : tblGameRoomEntities) {

                    String roomName = tblGameRoomEntity.getRoomName();
                    String roomNamePinYin = PinyinUtil.getPinYin(roomName);

                    MenuMetaVo menuMetaVoTwo = new MenuMetaVo();
                    menuMetaVoTwo.setTitle(roomName);
                    menuMetaVoTwo.setPath(roomNamePinYin);
                    menuMetaVoTwo.setAuth(true);

                    MenuVo menuVoTwo = new MenuVo();
                    menuVoTwo.setPath(roomNamePinYin);
                    menuVoTwo.setComponent("() => import('@/views/robotManager/robots')");
                    menuVoTwo.setName(tblGameRoomEntity.getRoomid().toString());
                    menuVoTwo.setMeta(menuMetaVoTwo);

                    menuVoOne.add(menuVoTwo);
                }
                menuVoOne.setComponent("() => import('@/views/robotManager/robots')");
                menuVo.add(menuVoOne);
            }


        }

        String s1 = JSON.toJSONString(menuVo);

        String replace = s1.replace("#\"", "'").replace("\"#", "'");
        String s = replace.replaceAll("\"", "");



        log.info(s);

        return menuVo;
    }

}
