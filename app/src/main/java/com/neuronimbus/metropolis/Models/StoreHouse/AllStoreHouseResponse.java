package com.neuronimbus.metropolis.Models.StoreHouse;


import java.util.ArrayList;

public class AllStoreHouseResponse {
    private String code;
    private String msg;
    private String titlename;
    ArrayList<data>data;
    ArrayList<InfoStoreCategory>InfoStoreCategory;

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }
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

    public ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.data> getData() {
        return data;
    }

    public void setData(ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.data> data) {
        this.data = data;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.InfoStoreCategory> getInfoStoreCategory() {
        return InfoStoreCategory;
    }

    public void setInfoStoreCategory(ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.InfoStoreCategory> infoStoreCategory) {
        InfoStoreCategory = infoStoreCategory;
    }
}
