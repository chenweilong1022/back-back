package com.ozygod.model.zdmanage.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ManageLogEntity {
    private Integer recordid;

    private Integer typeid;

    private Integer managerid;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date manageTime;

    private String manageDesc;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public Date getManageTime() {
        return manageTime;
    }

    public void setManageTime(Date manageTime) {
        this.manageTime = manageTime;
    }

    public String getManageDesc() {
        return manageDesc;
    }

    public void setManageDesc(String manageDesc) {
        this.manageDesc = manageDesc == null ? null : manageDesc.trim();
    }
}