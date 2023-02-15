package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class NotificationResponse {
    private String code;
    private String msg;
    ArrayList<NotificationList>NotificationList;

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

    public ArrayList<com.example.tooshytoask.Models.NotificationList> getNotificationList() {
        return NotificationList;
    }

    public void setNotificationList(ArrayList<com.example.tooshytoask.Models.NotificationList> notificationList) {
        NotificationList = notificationList;
    }
}
