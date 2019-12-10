package com.ozygod.account.web;

import com.ozygod.account.service.IManagerService;
import com.ozygod.account.service.IMenuService;
import com.ozygod.account.utils.ManageConstant;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.Constant;
import com.ozygod.base.utils.MD5Util;
import com.ozygod.model.zdmanage.bo.ManagerBO;
import com.ozygod.model.zdmanage.dto.LoginDto;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.RoleEntity;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/5
 */
@Slf4j
@RequestMapping("/managers")
@RestController
@Api(value = "/managers", description = "管理员帐号管理")
public class ManagerController {

    @Autowired
    private IManagerService managerService;

    @Autowired
    private IMenuService menuService;

    /**
     * 管理员登录
     * @param loginDto
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO login(@RequestBody LoginDto loginDto, HttpServletRequest request){
        if (CommonUtil.isEmptyStr(loginDto.getUsername())) {
            return new ResponseBO(ResponseCode.P001.getCode(), "用户名不能为空");
        }
        if (CommonUtil.isEmptyStr(loginDto.getPassword())) {
            return new ResponseBO(ResponseCode.P001.getCode(), "密码不能为空");
        }

        String browser = CommonUtil.getRequestBrowserInfo(request);
        String system = CommonUtil.getRequestSystemInfo(request);

        loginDto.setUserAgent(system+ "下的"+ browser);

        ManagerBO managerBO = managerService.login(loginDto);
        if (managerBO == null) {
            return new ResponseBO(ResponseCode.L003.getCode(),ResponseCode.L003.getTitle());
        }

        return new ResponseBO(managerBO);
    }



    /**
     * 查询管理员信息
     * @param managerId
     * @return
     */
    @RequestMapping(value = "/{managerId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO getManagerInfo(@PathVariable Integer managerId){

        ManagerBO managerBO = managerService.getManagerById(managerId);

        if (managerBO == null) {
            return new ResponseBO(ResponseCode.L002.getCode(), ResponseCode.L002.getTitle());
        }

        managerBO.setMenus(menuService.listMenuByRoleId(managerBO.getRoleid()));

        return new ResponseBO(managerBO);
    }

    /**
     * 修改管理员帐号密码
     * @param managerId
     * @param managerBO
     * @return
     */
    @RequestMapping(value = "/{managerId}/changePassword", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO changePassword(@PathVariable Integer managerId, @RequestBody ManagerBO managerBO) {

        managerBO.setManageid(managerId);
        int count = managerService.validateManager(managerBO);
        //验证不通过，返回错误提示
        if (count == 0) {
            return new ResponseBO(ResponseCode.L004.getCode(), ResponseCode.L004.getTitle());
        }

        //验证通过，修改密码
        int result = managerService.changePassword(managerBO);
        if (result == 2) {
            return new ResponseBO(ResponseCode.L002.getCode(), ResponseCode.L002.getTitle());
        } else if (result == 3){
            return new ResponseBO(ResponseCode.L004.getCode(), ResponseCode.L004.getTitle());
        }

        return new ResponseBO("密码修改成功");
    }

    /**
     * 重置管理帐号密码
     * @param managerId
     * @return
     */
    @RequestMapping(value = "/{managerId}/resetPassword", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO resetPassword(@PathVariable Integer managerId, @RequestBody ManagerBO bo) {
        bo.setManageid(managerId);

        int result = managerService.resetPassword(bo);
        if (result <= 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "重置失败");
        }

        return new ResponseBO("重置成功");
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/{managerId}/logout", method = RequestMethod.DELETE, headers = Constant.API_VERSION_V1)
    public ResponseBO logOut(@PathVariable Integer managerId, HttpServletRequest request) {
        String token = request.getHeader("X-Token");

        managerService.logout(token, managerId);

        return new ResponseBO("退出成功");
    }

    /**
     * 查询管理员列表
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listManagerByQry() {
        return new ResponseBO(managerService.listManagerByQry(null));
    }

    /**
     * 启用/禁用管理用户
     * @param managerId
     * @param dto
     * @return
     */
    @RequestMapping(value = "/{managerId}/transform", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO delManagerByQry(@PathVariable Integer managerId, @RequestBody ManagerDto dto) {
        dto.setManagerId(managerId);
        int result = managerService.delManagerByQry(dto);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "操作失败");
        }
        return new ResponseBO("操作成功");
    }

    /**
     * 新增管理账户
     * @param bo
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO addManager(@RequestBody ManagerBO bo) {
        int result = managerService.addManager(bo);
        if (result == -1) {
            return new ResponseBO(ResponseCode.I001.getCode(), "登录名重复，新增失败！");
        } else if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "新增失败！");
        }
        return new ResponseBO("新增成功");
    }

    /**
     * 修改管理账户
     * @param bo
     * @return
     */
    @RequestMapping(value = "/{managerId}", method = RequestMethod.PUT, headers = Constant.API_VERSION_V1)
    public ResponseBO updateManager(@PathVariable Integer managerId, @RequestBody ManagerBO bo) {
        bo.setManageid(managerId);
        int result = managerService.updateManager(bo);
        if (result <= 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "修改失败");
        }
        return new ResponseBO("修改成功");
    }

    /**
     * 查询管理日志类型列表
     * @return
     */
    @RequestMapping(value = "/logType", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listManageLogType() {
        return new ResponseBO(managerService.listManageLogType());
    }

    /**
     * 查询管理日志类型列表
     * @return
     */
    @RequestMapping(value = "/logs", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listManageLogByQry(@RequestBody ManagerDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(managerService.listManageLogByQry(dto));
        responseBO.setTotalCount(managerService.totalManageLogByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }



}
