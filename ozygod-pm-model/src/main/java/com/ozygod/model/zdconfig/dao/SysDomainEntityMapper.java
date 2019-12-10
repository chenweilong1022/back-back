package com.ozygod.model.zdconfig.dao;

import com.ozygod.model.zdconfig.bo.SysDomainBO;
import com.ozygod.model.zdconfig.dto.ConfigDto;
import com.ozygod.model.zdconfig.entity.SysDomainEntity;

import java.util.List;

public interface SysDomainEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDomainEntity record);

    int insertSelective(SysDomainEntity record);

    SysDomainEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDomainEntity record);

    int updateByPrimaryKey(SysDomainEntity record);

    /**
     * 查询域名列表
     * @param dto
     * @return
     */
    List<SysDomainBO> listSysDomainByQry(ConfigDto dto);

    /**
     * 查询域名列表总数
     * @param dto
     * @return
     */
    int totalSysDomainByQry(ConfigDto dto);
}