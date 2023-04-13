package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class SingleBlogResponse {
    private String code;
    private String msg;
    private String index;
    private String totalblogs;
    private String nextblog;
    private String previousblog;
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

    public ArrayList<com.neuronimbus.metropolis.Models.singleblog> getSingleblog() {
        return singleblog;
    }

    public void setSingleblog(ArrayList<com.neuronimbus.metropolis.Models.singleblog> singleblog) {
        this.singleblog = singleblog;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.relatedblogs> getRelatedblogs() {
        return relatedblogs;
    }

    public void setRelatedblogs(ArrayList<com.neuronimbus.metropolis.Models.relatedblogs> relatedblogs) {
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

    public String getNextblog() {
        return nextblog;
    }

    public void setNextblog(String nextblog) {
        this.nextblog = nextblog;
    }

    public String getPreviousblog() {
        return previousblog;
    }

    public void setPreviousblog(String previousblog) {
        this.previousblog = previousblog;
    }
}
