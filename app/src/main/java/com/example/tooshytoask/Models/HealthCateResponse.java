package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class HealthCateResponse {
    private String code;
    private String msg;

    ArrayList<InformationStorehouseList>InformationStorehouseList;


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

    public ArrayList<com.example.tooshytoask.Models.InformationStorehouseList> getInformationStorehouseList() {
        return InformationStorehouseList;
    }

    public void setInformationStorehouseList(ArrayList<com.example.tooshytoask.Models.InformationStorehouseList> informationStorehouseList) {
        InformationStorehouseList = informationStorehouseList;
    }
}
