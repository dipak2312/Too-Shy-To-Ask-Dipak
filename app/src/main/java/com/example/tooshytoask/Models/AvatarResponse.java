package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class AvatarResponse {
    private String code;
    private String msg;
    ArrayList<avatarList>avatarLists;
    int img;
    public Boolean isSelected = false;

    public AvatarResponse(int img, Boolean isSelected) {
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
