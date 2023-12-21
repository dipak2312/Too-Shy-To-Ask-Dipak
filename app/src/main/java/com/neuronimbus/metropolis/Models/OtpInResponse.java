package com.neuronimbus.metropolis.Models;

import android.content.Intent;

public class OtpInResponse {
    private String code;
    private String msg;
    private String usertype;
    private Boolean admin_approval;
    private data data;

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
    public data getData() {
        return data;
    }
    public void setData(data data) {
        this.data = data;
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
