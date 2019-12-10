package com.ozygod.account.service;

import com.ozygod.model.zdmanage.bo.MenuBO;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/10
 */
public interface IMenuService {
    /**
     * 根据角色获取菜单列表
     * @param roleId
     * @return
     */
    List<MenuBO> listMenuByRoleId(Integer roleId);

    /**
     * 查询全部菜单列表
     * @return
     */
    List<MenuBO> listAllMenus();

    /**
     * 保存角色菜单关联
     * @param bo
     * @return
     */
    int saveRoleMenu(MenuBO bo);

    /**
     * 保存菜单
     * @param bo
     * @return
     */
    int saveMenu(MenuBO bo);
}
