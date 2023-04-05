package com.example.tooshytoask.Models;

import java.util.ArrayList;
import java.util.List;

public class HealthIssueResponse {

    private String code;
    private String msg;
    private String IsHealthIssue;
    private ArrayList<HealthIssuseList>HealthIssuseList;

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

    public ArrayList<com.example.tooshytoask.Models.HealthIssuseList> getHealthIssuseList() {
        return HealthIssuseList;
    }

    public void setHealthIssuseList(ArrayList<com.example.tooshytoask.Models.HealthIssuseList> healthIssuseList) {
        HealthIssuseList = healthIssuseList;
    }

    public String getIsHealthIssue() {
        return IsHealthIssue;
    }

    public void setIsHealthIssue(String isHealthIssue) {
        IsHealthIssue = isHealthIssue;
    }
}
