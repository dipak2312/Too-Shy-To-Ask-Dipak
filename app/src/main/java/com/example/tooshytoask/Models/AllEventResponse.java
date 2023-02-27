package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class AllEventResponse {
    private String code;
    private String msg;
    ArrayList<insightevents>insightevents;

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

    public ArrayList<com.example.tooshytoask.Models.insightevents> getInsightevents() {
        return insightevents;
    }

    public void setInsightevents(ArrayList<com.example.tooshytoask.Models.insightevents> insightevents) {
        this.insightevents = insightevents;
    }
}
