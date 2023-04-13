package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class HomeScreenResponse {
    private String code;
    private String msg;
    private ArrayList<StoryCategory> StoryCategory;
    private ArrayList<Bannerist> Bannerist;
    private ArrayList<RecommendedBlogs> RecommendedBlogs;
    private ArrayList<Blogs> Blogs;

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

    public ArrayList<com.neuronimbus.metropolis.Models.StoryCategory> getStoryCategory() {
        return StoryCategory;
    }

    public void setStoryCategory(ArrayList<com.neuronimbus.metropolis.Models.StoryCategory> storyCategory) {
        StoryCategory = storyCategory;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.Bannerist> getBannerist() {
        return Bannerist;
    }

    public void setBannerist(ArrayList<com.neuronimbus.metropolis.Models.Bannerist> bannerist) {
        Bannerist = bannerist;
    }

    public ArrayList<RecommendedBlogs> getRecommendedBlogs() {
        return RecommendedBlogs;
    }

    public void setRecommendedBlogs(ArrayList<RecommendedBlogs> RecommendedBlogs) {
        this.RecommendedBlogs = RecommendedBlogs;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.Blogs> getBlogs() {
        return Blogs;
    }

    public void setBlogs(ArrayList<com.neuronimbus.metropolis.Models.Blogs> blogs) {
        Blogs = blogs;
    }
}
