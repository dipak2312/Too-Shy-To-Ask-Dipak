package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class StoreHouseRelatedResponse {
    private String code;
    private String msg;
    ArrayList<relatedstorehouse>relatedstorehouse;

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

    public ArrayList<com.example.tooshytoask.Models.relatedstorehouse> getRelatedstorehouse() {
        return relatedstorehouse;
    }

    public void setRelatedstorehouse(ArrayList<com.example.tooshytoask.Models.relatedstorehouse> relatedstorehouse) {
        this.relatedstorehouse = relatedstorehouse;
    }
}
