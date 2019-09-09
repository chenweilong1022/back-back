package com.ozygod.model.zdmanage.entity;

public class AgentRealtimeEntity {
    private Long userId;

    private Long superId;

    private Long totalPerformance;

    private Integer agentLevel;

    private Integer ratio;

    private Long rebate;

    private Long performance;

    private Long directPerformance;

    private Long teamPerformance;

    private Integer underCount;

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

    public Long getRebate() {
        return rebate;
    }

    public void setRebate(Long rebate) {
        this.rebate = rebate;
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
}