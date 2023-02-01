package com.example.tooshytoask.Models;

public class avatarList {
    private String encimg;
    public Boolean isSelected = false;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public avatarList(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getEncimg() {
        return encimg;
    }

    public void setEncimg(String encimg) {
        this.encimg = encimg;
    }

}
