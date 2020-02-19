package com.ozygod.model.zdconfig.dao;

import com.ozygod.model.zdmanage.entity.RoleMenuEntity;

public interface RoleMenuEntityMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(RoleMenuEntity record);

    int insertSelective(RoleMenuEntity record);

    RoleMenuEntity selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(RoleMenuEntity record);

    int updateByPrimaryKey(RoleMenuEntity record);
}
