package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.entity.ManageLogTypeEntity;

import java.util.List;

public interface ManageLogTypeEntityMapper {
    int deleteByPrimaryKey(Integer typeid);

    int insert(ManageLogTypeEntity record);

    int insertSelective(ManageLogTypeEntity record);

    ManageLogTypeEntity selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(ManageLogTypeEntity record);

    int updateByPrimaryKey(ManageLogTypeEntity record);

    /**
     * 查询管理日志类型列表
     * @return
     */
    List<ManageLogTypeEntity> listManageLogType();
}