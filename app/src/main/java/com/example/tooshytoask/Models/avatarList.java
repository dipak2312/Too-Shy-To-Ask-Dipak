package com.example.tooshytoask.Models;

public class avatarList {
    private String encimg;
    private String url;
    public Boolean isSelected = false;


    public String getEncimg() {
        return encimg;
    }

    public void setEncimg(String encimg) {
        this.encimg = encimg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
