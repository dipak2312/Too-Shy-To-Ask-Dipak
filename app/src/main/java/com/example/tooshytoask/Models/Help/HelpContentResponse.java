package com.example.tooshytoask.Models.Help;

import java.util.ArrayList;

public class HelpContentResponse {
    private String code;
    private String msg;
    private String title;
    private String helpcontent_title;
    private String helpcontent_desc;
    ArrayList<helpcontent>helpcontent;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHelpcontent_title() {
        return helpcontent_title;
    }

    public void setHelpcontent_title(String helpcontent_title) {
        this.helpcontent_title = helpcontent_title;
    }

    public String getHelpcontent_desc() {
        return helpcontent_desc;
    }

    public void setHelpcontent_desc(String helpcontent_desc) {
        this.helpcontent_desc = helpcontent_desc;
    }

    public ArrayList<com.example.tooshytoask.Models.Help.helpcontent> getHelpcontent() {
        return helpcontent;
    }

    public void setHelpcontent(ArrayList<com.example.tooshytoask.Models.Help.helpcontent> helpcontent) {
        this.helpcontent = helpcontent;
    }
}
