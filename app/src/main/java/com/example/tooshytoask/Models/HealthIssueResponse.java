package com.example.tooshytoask.Models;

import java.util.ArrayList;
import java.util.List;

public class HealthIssueResponse {

    private String code;
    private String msg;
    private ArrayList<HealthIssuseList> healthIssuseList = new ArrayList<HealthIssuseList>();
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
    public ArrayList<HealthIssuseList> getHealthIssuseList() {
        return healthIssuseList;
    }
    public void setHealthIssuseList(ArrayList<HealthIssuseList> healthIssuseList) {
        this.healthIssuseList = healthIssuseList;
    }
}
