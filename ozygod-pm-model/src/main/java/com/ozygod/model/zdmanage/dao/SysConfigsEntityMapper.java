package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.bo.SysConfigsBO;
import com.ozygod.model.zdmanage.dto.ConfigDto;
import com.ozygod.model.zdmanage.entity.SysConfigsEntity;

import java.util.List;

public interface SysConfigsEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfigsEntity record);

    int insertSelective(SysConfigsEntity record);

    SysConfigsEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfigsEntity record);

    int updateByPrimaryKey(SysConfigsEntity record);

    /**
     * 查询系统配置列表
     * @param dto
     * @return
     */
    List<SysConfigsEntity> listSysConfigsByQry(ConfigDto dto);

    /**
     * 查询系统配置列表总数
     * @param dto
     * @return
     */
    int totalSysConfigsByQry(ConfigDto dto);

    /**
     * 获取系统配置项详情
     * @param code
     * @return
     */
    SysConfigsBO getSysConfigsByQry(String code);
}