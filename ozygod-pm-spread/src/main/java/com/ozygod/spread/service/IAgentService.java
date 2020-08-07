package com.ozygod.spread.service;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdmanage.bo.AgentRealtimeBO;
import com.ozygod.model.zdmanage.bo.AgentRecordBO;
import com.ozygod.model.zdmanage.bo.AgentSummaryBO;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-08
 */
public interface IAgentService {
    /**
     * 查询实时代理记录列表
     * @param dto
     * @return
     */
    List<AgentRealtimeBO> listAgentRealtimeByQry(PlatformDto dto);

    /**
     * 查询实时代理记录列表
     * @param dto
     * @return
     */
    List<AgentRealtimeBO> listTotalAgentRealtimeByQry(PlatformDto dto);

    /**
     * 查询实时代理记录总数
     * @param dto
     * @return
     */
    int totalAgentRealtimeByQry(PlatformDto dto);

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
