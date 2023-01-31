package com.example.tooshytoask.Models;

public class StoryCategory {
    private String categoryId;
    private String categoryTitle;
    private String categoryImg;
    private Integer categoryStorycount;

    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryTitle() {
        return categoryTitle;
    }
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
    public String getCategoryImg() {
        return categoryImg;
    }
    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }
    public Integer getCategoryStorycount() {
        return categoryStorycount;
    }
    public void setCategoryStorycount(Integer categoryStorycount) {
        this.categoryStorycount = categoryStorycount;
    }
}
