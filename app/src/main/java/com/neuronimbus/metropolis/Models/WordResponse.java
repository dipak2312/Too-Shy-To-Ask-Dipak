package com.neuronimbus.metropolis.Models;

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

    public ArrayList<com.neuronimbus.metropolis.Models.GetWordList> getGetWordList() {
        return GetWordList;
    }

    public void setGetWordList(ArrayList<com.neuronimbus.metropolis.Models.GetWordList> getWordList) {
        GetWordList = getWordList;
    }
}
