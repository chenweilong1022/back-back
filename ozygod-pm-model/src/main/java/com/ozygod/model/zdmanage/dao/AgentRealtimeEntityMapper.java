package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdmanage.bo.AgentRealtimeBO;
import com.ozygod.model.zdmanage.entity.AgentRealtimeEntity;

import java.util.List;

public interface AgentRealtimeEntityMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(AgentRealtimeEntity record);

    int insertSelective(AgentRealtimeEntity record);

    AgentRealtimeEntity selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(AgentRealtimeEntity record);

    int updateByPrimaryKey(AgentRealtimeEntity record);

    /**
     * 查询实时代理记录列表
     * @param dto
     * @return
     */
    AgentRealtimeBO listTotalAgentRealtimeByQry(PlatformDto dto);

    /**
     * 查询实时代理记录列表
     * @param dto
     * @return
     */
    List<AgentRealtimeBO> listAgentRealtimeByQry(PlatformDto dto);



    /**
     * 查询实时代理记录总数
     * @param dto
     * @return
     */
    int totalAgentRealtimeByQry(PlatformDto dto);
}
