package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class AllHighlightResponse {
    private String code;
    private String msg;
    ArrayList<insighthighlights>insighthighlights;

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

    public ArrayList<com.example.tooshytoask.Models.insighthighlights> getInsighthighlights() {
        return insighthighlights;
    }

    public void setInsighthighlights(ArrayList<com.example.tooshytoask.Models.insighthighlights> insighthighlights) {
        this.insighthighlights = insighthighlights;
    }
}
