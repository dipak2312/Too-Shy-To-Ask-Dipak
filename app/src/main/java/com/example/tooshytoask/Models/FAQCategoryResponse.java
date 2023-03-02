package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class FAQCategoryResponse {
    private String code;
    private String msg;
    ArrayList<faqcategory>faqcategory;

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

    public ArrayList<com.example.tooshytoask.Models.faqcategory> getFaqcategory() {
        return faqcategory;
    }

    public void setFaqcategory(ArrayList<com.example.tooshytoask.Models.faqcategory> faqcategory) {
        this.faqcategory = faqcategory;
    }
}
