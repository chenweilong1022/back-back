package com.ozygod.spread.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.spread.service.IAgentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-08
 */
@Slf4j
@RestController
@RequestMapping("/agent")
@Api(value = "/agent", description = "代理系统")
public class AgentController implements Serializable {
    private static final long serialVersionUID = -8946122385953758181L;

    @Autowired
    private IAgentService agentService;

    /**
     * 查询代理结算记录列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/realtime/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listAgentRealtimeByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(agentService.listAgentRealtimeByQry(dto));
        responseBO.setTotalCount(agentService.totalAgentRealtimeByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询代理结算记录列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/record/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listAgentRecordByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(agentService.listAgentRecordByQry(dto));
        responseBO.setTotalCount(agentService.totalAgentRecordByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询代理结算累计记录列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/record/list/summary", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listAgentRecordSummaryByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(agentService.listAgentRecordSummaryByQry(dto));
        responseBO.setTotalCount(agentService.totalAgentRecordSummaryByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询代理结算记录总计
     * @param dto
     * @return
     */
    @RequestMapping(value = "/record/summary/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO getAgentRecordSummary(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(agentService.getAgentRecordSummary(dto));
        return responseBO;
    }
}
