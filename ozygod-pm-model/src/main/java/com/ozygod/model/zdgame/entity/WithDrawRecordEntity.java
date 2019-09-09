package com.ozygod.model.zdgame.entity;

public class WithDrawRecordEntity {
    private Long outtradeno;

    private String state;

    private Integer orderprice;

    private Integer userid;

    private String error;

    private String completetime;

    private String starttime;

    private String type;

    public Long getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(Long outtradeno) {
        this.outtradeno = outtradeno;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Integer orderprice) {
        this.orderprice = orderprice;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime == null ? null : completetime.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}