package com.example.tooshytoask.Models;

public class CategoryItem {
    int cate_img;
    String category_title;
    public Boolean isSelected = false;

    public CategoryItem(int cate_img, String category_title,Boolean selected) {
        this.cate_img = cate_img;
        this.category_title = category_title;
        this.isSelected=selected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getCate_img() {
        return cate_img;
    }

    public void setCate_img(int cate_img) {
        this.cate_img = cate_img;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }
}
