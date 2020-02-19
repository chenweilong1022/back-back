package com.ozygod.account.service.impl;

import com.ozygod.account.service.IRoleService;
import com.ozygod.model.zdconfig.dao.RoleEntityMapper;
import com.ozygod.model.zdmanage.bo.RoleBO;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/10/1
 */
@Component
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;


    /**
     * 查询角色列表
     *
     * @param managerDto
     * @return
     */
    @Override
    public List<RoleEntity> listRoleByQry(ManagerDto managerDto) {
        return roleEntityMapper.listRoleByQry(managerDto);
    }

    /**
     * 启用/禁用角色
     *
     * @param managerDto
     * @return
     */
    @Override
    public int transformRoleByQry(ManagerDto managerDto) {
        return roleEntityMapper.delRoleByQry(managerDto);
    }

    /**
     * 新增或修改角色
     *
     * @param bo
     * @return
     */
    @Override
    public int addOrUpdateRole(RoleBO bo) {
        return roleEntityMapper.addOrUpdateRole(bo);
    }

    /**
     * 保存角色渠道
     *
     * @param bo
     * @return
     */
    @Override
    public int saveAppRoleChannel(RoleBO bo) {
        return roleEntityMapper.saveAppRoleChannel(bo);
    }
}
