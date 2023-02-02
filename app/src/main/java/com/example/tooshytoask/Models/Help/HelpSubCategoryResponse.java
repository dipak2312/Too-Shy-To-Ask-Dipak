package com.example.tooshytoask.Models.Help;

import java.util.ArrayList;

public class HelpSubCategoryResponse {
    private String code;
    private String msg;
    private String helpcategory;
    private ArrayList<helpsubcategory>helpsubcategory;


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

    public String getHelpcategory() {
        return helpcategory;
    }

    public void setHelpcategory(String helpcategory) {
        this.helpcategory = helpcategory;
    }

    public ArrayList<com.example.tooshytoask.Models.Help.helpsubcategory> getHelpsubcategory() {
        return helpsubcategory;
    }

    public void setHelpsubcategory(ArrayList<com.example.tooshytoask.Models.Help.helpsubcategory> helpsubcategory) {
        this.helpsubcategory = helpsubcategory;
    }
}
