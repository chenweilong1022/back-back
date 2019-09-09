package com.ozygod.account.service;

import com.ozygod.model.zdmanage.bo.RoleBO;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.RoleEntity;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/10/1
 */
public interface IRoleService {
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
    int transformRoleByQry(ManagerDto managerDto);

    /**
     * 新增或修改角色
     * @param bo
     * @return
     */
    int addOrUpdateRole(RoleBO bo);

    /**
     * 保存角色渠道
     * @param bo
     * @return
     */
    int saveAppRoleChannel(RoleBO bo);
}
