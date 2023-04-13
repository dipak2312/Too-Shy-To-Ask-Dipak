package com.neuronimbus.metropolis.Models;

public class avatarList {
    private String encimg;
    private String url;
    //public int singleitem_selection_position = -1;
    public Boolean isSelected = false;

    public avatarList(String encimg, String url, Boolean isSelected) {
        this.encimg = encimg;
        this.url = url;
        this.isSelected = isSelected;
    }

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
