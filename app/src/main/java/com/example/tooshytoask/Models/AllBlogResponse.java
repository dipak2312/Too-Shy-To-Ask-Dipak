package com.example.tooshytoask.Models;

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


    public ArrayList<com.example.tooshytoask.Models.articleblogs> getArticleblogs() {
        return articleblogs;
    }

    public void setArticleblogs(ArrayList<com.example.tooshytoask.Models.articleblogs> articleblogs) {
        this.articleblogs = articleblogs;
    }
}
