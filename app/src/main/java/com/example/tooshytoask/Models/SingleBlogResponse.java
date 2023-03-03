package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class SingleBlogResponse {
    private String code;
    private String msg;
    private String index;
    private String totalblogs;
    ArrayList<singleblog>singleblog;
    ArrayList<relatedblogs>relatedblogs;


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

    public ArrayList<com.example.tooshytoask.Models.singleblog> getSingleblog() {
        return singleblog;
    }

    public void setSingleblog(ArrayList<com.example.tooshytoask.Models.singleblog> singleblog) {
        this.singleblog = singleblog;
    }

    public ArrayList<com.example.tooshytoask.Models.relatedblogs> getRelatedblogs() {
        return relatedblogs;
    }

    public void setRelatedblogs(ArrayList<com.example.tooshytoask.Models.relatedblogs> relatedblogs) {
        this.relatedblogs = relatedblogs;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTotalblogs() {
        return totalblogs;
    }

    public void setTotalblogs(String totalblogs) {
        this.totalblogs = totalblogs;
    }
}
