package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class WordResponse {
    private String code;
    private String msg;
    ArrayList<GetWordList>GetWordList;

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

    public ArrayList<com.example.tooshytoask.Models.GetWordList> getGetWordList() {
        return GetWordList;
    }

    public void setGetWordList(ArrayList<com.example.tooshytoask.Models.GetWordList> getWordList) {
        GetWordList = getWordList;
    }
}
