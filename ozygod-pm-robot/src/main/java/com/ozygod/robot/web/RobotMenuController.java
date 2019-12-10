package com.ozygod.robot.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.zdmanage.bo.RobotMenuBO;
import com.ozygod.model.zdmanage.dto.RobotMenuDto;
import com.ozygod.robot.service.IRobotMenuService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
@RequestMapping("/robots/menus")
@RestController
@Slf4j
@Api(value = "/robots/menus", description = "机器人与菜单映射管理")
public class RobotMenuController {
    
    @Autowired
    private IRobotMenuService robotMenuService;

    /**
     * 新增机器人菜单
     * @param menuBO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveRobotMenu(@RequestBody RobotMenuBO menuBO) {

        try {
            int count = robotMenuService.saveRobotMenu(menuBO);
            if (count == 0) {
                return new ResponseBO(ResponseCode.I001.getCode(), ResponseCode.I001.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存机器人菜单失败，原因：" + e.getMessage());
            return new ResponseBO(ResponseCode.I002.getCode(), ResponseCode.I002.getTitle());
        }

        return new ResponseBO("新增成功");
    }

    /**
     * 查询全部机器人菜单
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listAllRobotMenu() {
        return new ResponseBO(robotMenuService.listAllRobotMenu());
    }

    /**
     * 查询指定层级的机器人菜单
     * @return
     */
    @RequestMapping(value = "/{superId}/children", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listRobotMenuBySuperId(@PathVariable Integer superId) {
        RobotMenuDto dto = new RobotMenuDto();
        dto.setSuperId(superId);
        return new ResponseBO(robotMenuService.listRobotMenuByQry(dto));
    }

    /**
     * 根据指定条件查询机器人菜单
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRobotMenuByQry(@RequestBody RobotMenuDto dto) {
        return new ResponseBO(robotMenuService.listRobotMenuByQry(dto));
    }



    /**
     * 更新机器人菜单信息
     * @param id
     * @param robotMenuBO
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = Constant.API_VERSION_V1)
    public ResponseBO updateRobotMenu(@PathVariable Integer id, @RequestBody RobotMenuBO robotMenuBO) {
        robotMenuBO.setId(id);

        try {
            int count = robotMenuService.updateRobotMenu(robotMenuBO);
            if (count == 0) {
                return new ResponseBO(ResponseCode.U001.getCode(), ResponseCode.U001.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新机器人菜单信息失败，原因：", e.getMessage());
            return new ResponseBO(ResponseCode.U002.getCode(), ResponseCode.U002.getTitle());
        }

        return new ResponseBO("更新成功");
    }
}
