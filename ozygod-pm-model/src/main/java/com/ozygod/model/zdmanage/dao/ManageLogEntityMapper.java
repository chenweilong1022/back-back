package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.bo.ManageLogBO;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.ManageLogEntity;

import java.util.List;

public interface ManageLogEntityMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(ManageLogEntity record);

    int insertSelective(ManageLogEntity record);

    ManageLogEntity selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(ManageLogEntity record);

    int updateByPrimaryKey(ManageLogEntity record);

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