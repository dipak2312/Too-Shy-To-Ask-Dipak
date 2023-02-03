package com.example.tooshytoask.Models.Help;

import java.util.ArrayList;

public class HelpContentResponse {
    private String code;
    private String msg;
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

    public ArrayList<com.example.tooshytoask.Models.Help.helpcontent> getHelpcontent() {
        return helpcontent;
    }

    public void setHelpcontent(ArrayList<com.example.tooshytoask.Models.Help.helpcontent> helpcontent) {
        this.helpcontent = helpcontent;
    }
}
