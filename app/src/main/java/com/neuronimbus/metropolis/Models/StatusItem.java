package com.neuronimbus.metropolis.Models;

public class StatusItem {
    int status_img;
    String status_title;

    public StatusItem(int status_img, String status_title) {
        this.status_img = status_img;
        this.status_title = status_title;
    }


    public int getStatus_img() {
        return status_img;
    }

    public void setStatus_img(int status_img) {
        this.status_img = status_img;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }
}
