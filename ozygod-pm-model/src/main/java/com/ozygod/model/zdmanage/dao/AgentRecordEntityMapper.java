package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdmanage.bo.AgentRecordBO;
import com.ozygod.model.zdmanage.bo.AgentSummaryBO;
import com.ozygod.model.zdmanage.entity.AgentRecordEntity;

import java.util.List;

public interface AgentRecordEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgentRecordEntity record);

    int insertSelective(AgentRecordEntity record);

    AgentRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentRecordEntity record);

    int updateByPrimaryKey(AgentRecordEntity record);

    /**
     * 查询代理结算记录列表
     * @param dto
     * @return
     */
    List<AgentRecordBO> listAgentRecordByQry(PlatformDto dto);

    /**
     * 查询代理结算记录总数
     * @param dto
     * @return
     */
    int totalAgentRecordByQry(PlatformDto dto);

    /**
     * 查询代理结算汇总记录列表
     * @param dto
     * @return
     */
    List<AgentRecordBO> listAgentRecordSummaryByQry(PlatformDto dto);

    /**
     * 查询代理结算记录总数
     * @param dto
     * @return
     */
    int totalAgentRecordSummaryByQry(PlatformDto dto);

    /**
     * 查询代理结算记录总计
     * @param dto
     * @return
     */
    AgentSummaryBO getAgentRecordSummary(PlatformDto dto);
}