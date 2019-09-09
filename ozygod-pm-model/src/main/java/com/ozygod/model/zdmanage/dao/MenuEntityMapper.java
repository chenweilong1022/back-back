package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.bo.MenuBO;
import com.ozygod.model.zdmanage.entity.MenuEntity;

import java.util.List;

public interface MenuEntityMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(MenuEntity record);

    int insertSelective(MenuEntity record);

    MenuEntity selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);

    /**
     * 根据角色id获取菜单列表
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

    int saveMenu(MenuBO bo);
}