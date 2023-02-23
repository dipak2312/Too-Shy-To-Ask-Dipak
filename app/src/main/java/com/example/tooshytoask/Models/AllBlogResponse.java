package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class AllBlogResponse {
    private String code;
    private String msg;
    ArrayList<insightblogs>insightblogs;

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

    public ArrayList<com.example.tooshytoask.Models.insightblogs> getInsightblogs() {
        return insightblogs;
    }

    public void setInsightblogs(ArrayList<com.example.tooshytoask.Models.insightblogs> insightblogs) {
        this.insightblogs = insightblogs;
    }
}
