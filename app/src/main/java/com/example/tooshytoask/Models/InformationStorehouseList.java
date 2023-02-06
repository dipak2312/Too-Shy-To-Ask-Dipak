package com.example.tooshytoask.Models;

public class InformationStorehouseList {
    private String id;
    private String title;
    private String img;

    public Boolean isSelected = false;

    public InformationStorehouseList(String id, String title, String img, Boolean isSelected) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
