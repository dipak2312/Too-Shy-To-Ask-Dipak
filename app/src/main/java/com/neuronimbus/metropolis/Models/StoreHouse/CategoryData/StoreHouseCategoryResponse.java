package com.neuronimbus.metropolis.Models.StoreHouse.CategoryData;

import com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.data;

import java.util.ArrayList;

public class StoreHouseCategoryResponse {
    private String code;
    private String msg;
    ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.data>data;

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

    public ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.data> getData() {
        return data;
    }

    public void setData(ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.data> data) {
        this.data = data;
    }
}
