package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class HealthCateResponse {
    private String code;
    private String msg;

    ArrayList<InformationStorehouseList>informationStorehouseLists;


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

    public ArrayList<InformationStorehouseList> getInformationStorehouseLists() {
        return informationStorehouseLists;
    }

    public void setInformationStorehouseLists(ArrayList<InformationStorehouseList> informationStorehouseLists) {
        this.informationStorehouseLists = informationStorehouseLists;
    }
}
