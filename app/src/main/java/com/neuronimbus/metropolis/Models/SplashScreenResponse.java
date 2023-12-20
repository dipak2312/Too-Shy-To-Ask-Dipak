package com.neuronimbus.metropolis.Models;

public class SplashScreenResponse {
    private String code;
    private String msg;
    private String user_id;
    private String usertype;
    private Boolean admin_approval;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Boolean getAdmin_approval() {
        return admin_approval;
    }

    public void setAdmin_approval(Boolean admin_approval) {
        this.admin_approval = admin_approval;
    }
}
