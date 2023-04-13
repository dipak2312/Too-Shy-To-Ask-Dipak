package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class AllBlogResponse {
    private String code;
    private String msg;
    ArrayList<articleblogs>articleblogs;

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


    public ArrayList<com.neuronimbus.metropolis.Models.articleblogs> getArticleblogs() {
        return articleblogs;
    }

    public void setArticleblogs(ArrayList<com.neuronimbus.metropolis.Models.articleblogs> articleblogs) {
        this.articleblogs = articleblogs;
    }
}
