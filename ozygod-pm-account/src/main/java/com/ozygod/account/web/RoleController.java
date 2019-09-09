package com.ozygod.account.web;

import com.ozygod.account.service.IRoleService;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.zdmanage.bo.RoleBO;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.RoleEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/10/1
 */
@RequestMapping(value = "/roles")
@RestController
@Api(value = "/roles", description = "角色管理")
public class RoleController implements Serializable {
    private static final long serialVersionUID = 8633496155984041465L;

    @Autowired
    private IRoleService roleService;

    /**
     * 根据条件查询角色列表
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRoleByQry(@RequestBody ManagerDto managerDto) {
        return new ResponseBO(roleService.listRoleByQry(managerDto));
    }

    /**
     * 查询角色列表
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listRole() {
        return new ResponseBO(roleService.listRoleByQry(null));
    }

    /**
     * 查询角色详情
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO getRoleByQry(@PathVariable Integer roleId) {
        ManagerDto dto = new ManagerDto();
        dto.setRoleId(roleId);
        RoleEntity roleEntity = null;
        List<RoleEntity> result = roleService.listRoleByQry(dto);
        if (!CommonUtil.isEmpty(result)){
            roleEntity = result.get(0);
        }
        return new ResponseBO(roleEntity);
    }

    /**
     * 启用/禁用角色
     * @return
     */
    @RequestMapping(value = "/{roleId}/transform", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO transformRoleByQry(@PathVariable Integer roleId, @RequestBody ManagerDto dto) {
        dto.setRoleId(roleId);
        return new ResponseBO(roleService.transformRoleByQry(dto));
    }

    /**
     * 创建角色
     * @param roleBO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO addRole(@RequestBody RoleBO roleBO) {
        int result = roleService.addOrUpdateRole(roleBO);
        if (result == -1) {
            return new ResponseBO(ResponseCode.I001.getCode(), "角色名不能重复");
        } else if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "创建失败");
        }
        return new ResponseBO("创建成功");
    }

    /**
     * 修改角色
     * @param roleBO
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.PUT, headers = Constant.API_VERSION_V1)
    public ResponseBO updateRole(@PathVariable Integer roleId, @RequestBody RoleBO roleBO) {
        roleBO.setRoleid(roleId);
        int result = roleService.addOrUpdateRole(roleBO);
        if (result == -1) {
            return new ResponseBO(ResponseCode.I001.getCode(), "角色名不能重复");
        } else if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "修改失败");
        }
        return new ResponseBO("修改成功");
    }

    /**
     * 保存角色渠道
     * @param roleBO
     * @return
     */
    @RequestMapping(value = "/roleChannel", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveAppRoleChannel( @RequestBody RoleBO roleBO) {
        int result = roleService.saveAppRoleChannel(roleBO);
        if (result == 2) {
            return new ResponseBO(ResponseCode.I001.getCode(), "已存在录入的数据");
        } else if (result == 1) {
            return new ResponseBO(ResponseCode.I001.getCode(), "录入数据失败");
        }
        return new ResponseBO("录入数据成功");
    }

}
