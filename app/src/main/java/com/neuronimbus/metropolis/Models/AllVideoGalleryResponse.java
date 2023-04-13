package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class AllVideoGalleryResponse {
    private String code;
    private String msg;
    ArrayList<insightvideo>insightvideo;

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

    public ArrayList<com.neuronimbus.metropolis.Models.insightvideo> getInsightvideo() {
        return insightvideo;
    }

    public void setInsightvideo(ArrayList<com.neuronimbus.metropolis.Models.insightvideo> insightvideo) {
        this.insightvideo = insightvideo;
    }
}
