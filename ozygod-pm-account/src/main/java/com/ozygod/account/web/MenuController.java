package com.ozygod.account.web;

import com.ozygod.account.service.IMenuService;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.zdmanage.bo.MenuBO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/10
 */
@RequestMapping("/menus")
@RestController
@Slf4j
@Api(value = "/menus", description = "菜单管理")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 根据角色id查询菜单列表
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listMenuByRoleId(@PathVariable Integer roleId) {
        return new ResponseBO(menuService.listMenuByRoleId(roleId));
    }

    /**
     * 查询全部菜单列表
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listAllMenus() {
        return new ResponseBO(menuService.listAllMenus());
    }

    /**
     * 保存角色菜单关联
     * @return
     */
    @RequestMapping(value = "/roleMenu", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveRoleMenu(@RequestBody MenuBO bo) {
        int result = menuService.saveRoleMenu(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "保存失败");
        }
        return new ResponseBO("保存成功");
    }

    /**
     * 保存菜单
     * @return
     */
    @RequestMapping(value = "/{menuId}", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveMenu(@PathVariable Integer menuId,@RequestBody MenuBO bo) {
        bo.setMenuid(menuId);
        int result = menuService.saveMenu(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "保存失败");
        }
        return new ResponseBO("保存成功");
    }
}
