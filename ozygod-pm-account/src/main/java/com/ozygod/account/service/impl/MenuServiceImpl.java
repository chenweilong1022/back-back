package com.ozygod.account.service.impl;

import com.ozygod.account.service.IMenuService;
import com.ozygod.model.zdconfig.dao.MenuEntityMapper;
import com.ozygod.model.zdmanage.bo.MenuBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/10
 */
@Component
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuEntityMapper menuEntityMapper;

    /**
     * 根据角色获取菜单列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<MenuBO> listMenuByRoleId(Integer roleId) {
        return menuEntityMapper.listMenuByRoleId(roleId);
    }

    /**
     * 查询全部菜单列表
     *
     * @return
     */
    @Override
    public List<MenuBO> listAllMenus() {
        return menuEntityMapper.listAllMenus();
    }

    /**
     * 保存角色菜单关联
     *
     * @param bo
     * @return
     */
    @Override
    public int saveRoleMenu(MenuBO bo) {
        return menuEntityMapper.saveRoleMenu(bo);
    }

    /**
     * 保存菜单
     *
     * @param bo
     * @return
     */
    @Override
    public int saveMenu(MenuBO bo) {
        return menuEntityMapper.saveMenu(bo);
    }
}
