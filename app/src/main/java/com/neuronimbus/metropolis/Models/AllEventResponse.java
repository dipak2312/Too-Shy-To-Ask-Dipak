package com.neuronimbus.metropolis.Models;

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

    public ArrayList<com.neuronimbus.metropolis.Models.insightevents> getInsightevents() {
        return insightevents;
    }

    public void setInsightevents(ArrayList<com.neuronimbus.metropolis.Models.insightevents> insightevents) {
        this.insightevents = insightevents;
    }
}
