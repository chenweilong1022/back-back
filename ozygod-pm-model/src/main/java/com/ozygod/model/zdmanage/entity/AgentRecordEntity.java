package com.ozygod.model.zdmanage.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class AgentRecordEntity {
    private Long id;

    private Long userId;

    private Long superId;

    private int period;

    private Long totalPerformance;

    private Integer agentLevel;

    private Integer ratio;

    private Long totalRebate;

    private Long performance;

    private Long directPerformance;

    private Long teamPerformance;

    private Integer underCount;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date agentTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSuperId() {
        return superId;
    }

    public void setSuperId(Long superId) {
        this.superId = superId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Long getTotalPerformance() {
        return totalPerformance;
    }

    public void setTotalPerformance(Long totalPerformance) {
        this.totalPerformance = totalPerformance;
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Long getTotalRebate() {
        return totalRebate;
    }

    public void setTotalRebate(Long totalRebate) {
        this.totalRebate = totalRebate;
    }

    public Long getPerformance() {
        return performance;
    }

    public void setPerformance(Long performance) {
        this.performance = performance;
    }

    public Long getDirectPerformance() {
        return directPerformance;
    }

    public void setDirectPerformance(Long directPerformance) {
        this.directPerformance = directPerformance;
    }

    public Long getTeamPerformance() {
        return teamPerformance;
    }

    public void setTeamPerformance(Long teamPerformance) {
        this.teamPerformance = teamPerformance;
    }

    public Integer getUnderCount() {
        return underCount;
    }

    public void setUnderCount(Integer underCount) {
        this.underCount = underCount;
    }

    public Date getAgentTime() {
        return agentTime;
    }

    public void setAgentTime(Date agentTime) {
        this.agentTime = agentTime;
    }
}