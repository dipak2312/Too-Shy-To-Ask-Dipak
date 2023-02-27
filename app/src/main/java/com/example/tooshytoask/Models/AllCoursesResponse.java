package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class AllCoursesResponse {
    private String code;
    private String msg;
    ArrayList<insightcourses>insightcourses;

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

    public ArrayList<com.example.tooshytoask.Models.insightcourses> getInsightcourses() {
        return insightcourses;
    }

    public void setInsightcourses(ArrayList<com.example.tooshytoask.Models.insightcourses> insightcourses) {
        this.insightcourses = insightcourses;
    }
}
