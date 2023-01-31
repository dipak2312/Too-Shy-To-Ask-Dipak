package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class HomeScreenResponse {
    private String code;
    private String msg;
    private ArrayList<StoryCategory> storyCategories;
    private ArrayList<Bannerist> bannerist;
    private ArrayList<Object> recommendedBlogs;
    private ArrayList<Blogs> blogs;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public ArrayList<StoryCategory> getStoryCategory() {
        return storyCategories;
    }
    public void setStoryCategory(ArrayList<StoryCategory> storyCategories) {
        this.storyCategories = storyCategories;
    }
    public ArrayList<Bannerist> getBannerist() {
        return bannerist;
    }
    public void setBannerist(ArrayList<Bannerist> bannerist) {
        this.bannerist = bannerist;
    }
    public ArrayList<Object> getRecommendedBlogs() {
        return recommendedBlogs;
    }
    public void setRecommendedBlogs(ArrayList<Object> recommendedBlogs) {
        this.recommendedBlogs = recommendedBlogs;
    }
    public ArrayList<Blogs> getBlogs() {
        return blogs;
    }
    public void setBlogs(ArrayList<Blogs> blogs) {
        this.blogs = blogs;
    }
}
