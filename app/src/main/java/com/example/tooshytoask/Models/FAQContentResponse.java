package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class FAQContentResponse {
    private String code;
    private String msg;
    ArrayList<faqcontent>faqcontent;

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

    public ArrayList<com.example.tooshytoask.Models.faqcontent> getFaqcontent() {
        return faqcontent;
    }

    public void setFaqcontent(ArrayList<com.example.tooshytoask.Models.faqcontent> faqcontent) {
        this.faqcontent = faqcontent;
    }
}
