package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class BlogCategoryResponse {
    private String code;
    private String msg;
    ArrayList<insightblogcategories>insightblogcategories;

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

    public ArrayList<com.neuronimbus.metropolis.Models.insightblogcategories> getInsightblogcategories() {
        return insightblogcategories;
    }

    public void setInsightblogcategories(ArrayList<com.neuronimbus.metropolis.Models.insightblogcategories> insightblogcategories) {
        this.insightblogcategories = insightblogcategories;
    }
}
