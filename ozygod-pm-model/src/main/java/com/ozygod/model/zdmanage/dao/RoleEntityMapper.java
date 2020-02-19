package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.bo.RoleBO;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.RoleEntity;

import java.util.List;

public interface RoleEntityMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);

    /**
     * 查询角色列表
     * @param managerDto
     * @return
     */
    List<RoleEntity> listRoleByQry(ManagerDto managerDto);

    /**
     * 启用/禁用角色
     * @param managerDto
     * @return
     */
    int delRoleByQry(ManagerDto managerDto);

    /**
     * 新增或修改角色
     * @param roleBO
     * @return
     */
    int addOrUpdateRole(RoleBO roleBO);

    /**
     * 保存角色渠道
     * @param bo
     * @return
     */
    int saveAppRoleChannel(RoleBO bo);
}