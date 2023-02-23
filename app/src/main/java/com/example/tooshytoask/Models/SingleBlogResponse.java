package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class SingleBlogResponse {
    private String code;
    private String msg;
    ArrayList<singleblog>singleblog;


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
}
