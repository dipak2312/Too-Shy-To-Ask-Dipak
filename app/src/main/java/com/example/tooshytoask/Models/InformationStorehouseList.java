package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class InformationStorehouseList {
    private String id;
    private String title;
    private int img;
    private String slug;
    public Boolean isSelected = false;
    //ArrayList<usertype>usertypes

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
