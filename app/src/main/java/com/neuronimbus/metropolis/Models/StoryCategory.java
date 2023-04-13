package com.neuronimbus.metropolis.Models;

public class StoryCategory {
    private String category_id;
    private String category_title;
    private String category_img;
    private Integer category_storycount;


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_img() {
        return category_img;
    }

    public void setCategory_img(String category_img) {
        this.category_img = category_img;
    }

    public Integer getCategory_storycount() {
        return category_storycount;
    }

    public void setCategory_storycount(Integer category_storycount) {
        this.category_storycount = category_storycount;
    }
}
