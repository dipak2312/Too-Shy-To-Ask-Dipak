package com.example.tooshytoask.Models;

public class ProfileItems {
    int img;
    public Boolean isSelected = false;

    public ProfileItems(int img, Boolean isSelected) {
        this.img = img;
        this.isSelected = isSelected;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
