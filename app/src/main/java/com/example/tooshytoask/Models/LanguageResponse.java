package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class LanguageResponse {

    private String code;
    private String msg;

    public String getLanguageList() {
        return LanguageList;
    }

    public void setLanguageList(String languageList) {
        LanguageList = languageList;
    }

    private String LanguageList;
    //ArrayList<LanguageList>languageLists;

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

    /*public ArrayList<LanguageList> getLanguageLists() {
        return languageLists;
    }

    public void setLanguageLists(ArrayList<LanguageList> languageLists) {
        this.languageLists = languageLists;
    }*/
}
