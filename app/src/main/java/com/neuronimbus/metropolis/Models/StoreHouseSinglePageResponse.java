package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class StoreHouseSinglePageResponse {
    private String code;
    private String msg;
    ArrayList<storehousedata>storehousedata;

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

    public ArrayList<com.neuronimbus.metropolis.Models.storehousedata> getStorehousedata() {
        return storehousedata;
    }

    public void setStorehousedata(ArrayList<com.neuronimbus.metropolis.Models.storehousedata> storehousedata) {
        this.storehousedata = storehousedata;
    }
}
