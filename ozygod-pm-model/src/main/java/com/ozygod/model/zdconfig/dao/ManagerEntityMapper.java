package com.ozygod.model.zdconfig.dao;

import com.ozygod.model.zdmanage.bo.ManagerBO;
import com.ozygod.model.zdmanage.dto.LoginDto;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.ManagerEntity;

import java.util.List;

public interface ManagerEntityMapper {
    int insert(ManagerEntity record);

    int insertSelective(ManagerEntity record);

    /**
     * 根据登录名查询管理员信息
     * @param loginDto
     * @return
     */
    ManagerBO getManagerByLoginDto(LoginDto loginDto);

    /**
     * 根据登录名查询管理员信息
     * @param loginDto
     * @return
     */
    ManagerBO getManagerBySuperDto(LoginDto loginDto);

    /**
     * 登录
     * @param loginDto
     */
    void login(LoginDto loginDto);

    /**
     * 根据管理员id查询管理员信息
     * @param managerId
     * @return
     */
    ManagerBO getManagerById(Integer managerId);

    /**
     * 验证管理账户信息
     * @param managerBO
     * @return
     */
    int validateManager(ManagerBO managerBO);

    /**
     * 修改管理员密码
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
     * 新增或修改管理账户
     * @param bo
     * @return
     */
    int addOrUpdateManager(ManagerBO bo);
}
