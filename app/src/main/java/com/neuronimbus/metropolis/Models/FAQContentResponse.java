package com.neuronimbus.metropolis.Models;

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

    public ArrayList<com.neuronimbus.metropolis.Models.faqcontent> getFaqcontent() {
        return faqcontent;
    }

    public void setFaqcontent(ArrayList<com.neuronimbus.metropolis.Models.faqcontent> faqcontent) {
        this.faqcontent = faqcontent;
    }
}
