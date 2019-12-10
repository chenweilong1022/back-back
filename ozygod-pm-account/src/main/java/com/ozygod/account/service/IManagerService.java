package com.ozygod.account.service;

import com.ozygod.model.zdmanage.bo.ManageLogBO;
import com.ozygod.model.zdmanage.bo.ManagerBO;
import com.ozygod.model.zdmanage.dto.LoginDto;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.ManageLogTypeEntity;
import com.ozygod.model.zdmanage.entity.RoleEntity;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/5
 */
public interface IManagerService {
    /**
     * 根据登录名查询管理员信息
     * @param loginDto
     * @return
     */
    ManagerBO login(LoginDto loginDto);

    /**
     * 退出登录
     * @param token
     * @param userId
     */
    void logout(String token, Integer userId);

    /**
     * 根据管理员id查询管理员信息
     * @param managerId
     * @return
     */
    ManagerBO getManagerById(Integer managerId);

    /**
     * 验证管理员账户信息
     * @param managerBO
     * @return
     */
    int validateManager(ManagerBO managerBO);

    /**
     * 更新管理员账户
     * @param managerBO
     * @return
     */
    int changePassword(ManagerBO managerBO);

    /**
     * 查询管理人员列表
     * @param dto
     * @return
     */
    List<ManagerBO> listManagerByQry(ManagerDto dto);

    /**
     * 删除管理用户
     * @param dto
     * @return
     */
    int delManagerByQry(ManagerDto dto);

    /**
     * 重置管理员密码
     * @param bo
     * @return
     */
    int resetPassword(ManagerBO bo);

    /**
     * 新增管理员信息
     * @param bo
     * @return
     */
    int addManager(ManagerBO bo);

    /**
     * 修改管理员信息
     * @param bo
     * @return
     */
    int updateManager(ManagerBO bo);

    /**
     * 查询管理日志类型列表
     * @return
     */
    List<ManageLogTypeEntity> listManageLogType();



    /**
     * 查询管理日志列表
     * @param dto
     * @return
     */
    List<ManageLogBO> listManageLogByQry(ManagerDto dto);

    /**
     * 查询管理日志列表总数
     * @param dto
     * @return
     */
    int totalManageLogByQry(ManagerDto dto);

}
