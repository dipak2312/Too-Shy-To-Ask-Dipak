package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class ManageNotificationResponse {
    private String code;
    private String msg;
    ArrayList<dataNotification>dataNotification;

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

    public ArrayList<com.example.tooshytoask.Models.dataNotification> getDataNotification() {
        return dataNotification;
    }

    public void setDataNotification(ArrayList<com.example.tooshytoask.Models.dataNotification> dataNotification) {
        this.dataNotification = dataNotification;
    }
}
