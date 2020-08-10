package com.ozygod.spread.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdmanage.bo.AgentRealtimeBO;
import com.ozygod.model.zdmanage.bo.AgentRecordBO;
import com.ozygod.model.zdmanage.bo.AgentSummaryBO;
import com.ozygod.model.zdmanage.dao.AgentRealtimeEntityMapper;
import com.ozygod.model.zdmanage.dao.AgentRecordEntityMapper;
import com.ozygod.spread.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-08
 */
@Component
public class AgentServiceImpl implements IAgentService {
    @Autowired
    private AgentRealtimeEntityMapper agentRealtimeEntityMapper;
    @Autowired
    private AgentRecordEntityMapper agentRecordEntityMapper;

    /**
     * 查询实时代理记录列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<AgentRealtimeBO> listAgentRealtimeByQry(PlatformDto dto) {
        return agentRealtimeEntityMapper.listAgentRealtimeByQry(dto);
    }

    @Override
    public List<AgentRealtimeBO> listTotalAgentRealtimeByQry(PlatformDto dto) {

        List<AgentRealtimeBO> list = new ArrayList<>();
        AgentRealtimeBO agentRealtimeBO = agentRealtimeEntityMapper.listTotalAgentRealtimeByQry(dto);
        if (ObjectUtil.isNull(agentRealtimeBO)) {
            agentRealtimeBO = new AgentRealtimeBO();
        }
        agentRealtimeBO.setType("小计");
        list.add(agentRealtimeBO);

        AgentRealtimeBO agentRealtimeBO1 = agentRealtimeEntityMapper.listTotalAgentRealtimeByQry(null);
        if (ObjectUtil.isNull(agentRealtimeBO1)) {
            agentRealtimeBO1 = new AgentRealtimeBO();
        }
        agentRealtimeBO1.setType("总计");
        list.add(agentRealtimeBO1);

        return list;
    }

    /**
     * 查询实时代理记录总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalAgentRealtimeByQry(PlatformDto dto) {
        return agentRealtimeEntityMapper.totalAgentRealtimeByQry(dto);
    }

    /**
     * 查询代理结算记录列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<AgentRecordBO> listAgentRecordByQry(PlatformDto dto) {
//        if (ObjectUtil.isNull(dto.getSuperId())) {
//            dto.setSuperId(0L);
//        }
        List<AgentRecordBO> agentRecordBOS = agentRecordEntityMapper.listAgentRecordByQry(dto);
        agentRecordBOS.forEach(this::sub);
        return agentRecordBOS;
    }

    private void sub(AgentRecordBO agentRecordBO) {

        PlatformDto dto = new PlatformDto();
        dto.setSuperId(agentRecordBO.getUserId());
        dto.setPeriod(agentRecordBO.getPeriod());
        List<AgentRecordBO> agentRecordBOS = agentRecordEntityMapper.listAgentRecordByQry(dto);
        if (CollUtil.isNotEmpty(agentRecordBOS)) {
            agentRecordBO.setChildrens(agentRecordBOS);
            agentRecordBO.setChildrenCount(agentRecordBOS.size());
            agentRecordBOS.forEach(this::sub);
        }
    }

    /**
     * 查询代理结算记录总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalAgentRecordByQry(PlatformDto dto) {
        return agentRecordEntityMapper.totalAgentRecordByQry(dto);
    }

    /**
     * 查询代理结算汇总记录列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<AgentRecordBO> listAgentRecordSummaryByQry(PlatformDto dto) {
        this.getDateDurationByQueryType(dto);
        return agentRecordEntityMapper.listAgentRecordSummaryByQry(dto);
    }

    /**
     * 查询代理结算记录总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalAgentRecordSummaryByQry(PlatformDto dto) {
        this.getDateDurationByQueryType(dto);
        return agentRecordEntityMapper.totalAgentRecordSummaryByQry(dto);
    }

    /**
     * 根据查询类型获取日期间隔
     * @param dto
     */
    private void getDateDurationByQueryType(PlatformDto dto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 近7天
            if ("seven".equals(dto.getQueryType())) {
                LocalDate nowDate = LocalDate.now();
                dto.setEndTime(sdf.parse(nowDate.toString() + " 23:59:59"));

                LocalDate startDate = nowDate.minusDays(7);
                dto.setStartTime(sdf.parse(startDate.toString() + " 00:00:00"));
            } else if ("fifteen".equals(dto.getQueryType())) {
                LocalDate nowDate = LocalDate.now();
                dto.setEndTime(sdf.parse(nowDate.toString() + " 23:59:59"));

                LocalDate startDate = nowDate.minusDays(15);
                dto.setStartTime(sdf.parse(startDate.toString() + " 00:00:00"));
            } else if ("thirty".equals(dto.getQueryType())) {
                LocalDate nowDate = LocalDate.now();
                dto.setEndTime(sdf.parse(nowDate.toString() + " 23:59:59"));

                LocalDate startDate = nowDate.minusDays(30);
                dto.setStartTime(sdf.parse(startDate.toString() + " 00:00:00"));
            } else if ("currentMonth".equals(dto.getQueryType())) {
                LocalDate nowDate = LocalDate.now();
                String lastMonthDay = CommonUtil.getMonthLastDay(nowDate.toString());
                dto.setEndTime(sdf.parse(lastMonthDay + " 23:59:59"));

                String firstMonthDay = CommonUtil.getMonthFirstDay(nowDate.toString());
                dto.setStartTime(sdf.parse(firstMonthDay + " 00:00:00"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询代理结算记录总计
     *
     * @param dto
     * @return
     */
    @Override
    public AgentSummaryBO getAgentRecordSummary(PlatformDto dto) {
//        this.getDateDurationByQueryType(dto);
        return agentRecordEntityMapper.getAgentRecordSummary(dto);
    }
}
