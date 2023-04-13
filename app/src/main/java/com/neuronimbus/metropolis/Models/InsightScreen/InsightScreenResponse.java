package com.neuronimbus.metropolis.Models.InsightScreen;

import java.util.ArrayList;

public class InsightScreenResponse {
    private String code;
    private String msg;
    private ArrayList<storeHouse> storeHouse;
    private ArrayList<blogs>blogs;
    private ArrayList<higlights>higlights;
    private ArrayList<events>events;
    private ArrayList<new_blogs>new_blogs;
    private ArrayList<courses>courses;
    private ArrayList<video_gallery>video_gallery;


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

    public ArrayList<storeHouse> getBlog_category() {
        return storeHouse;
    }

    public void setBlog_category(ArrayList<storeHouse> storeHouse) {
        this.storeHouse = storeHouse;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.blogs> blogs) {
        this.blogs = blogs;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.higlights> getHiglights() {
        return higlights;
    }

    public void setHiglights(ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.higlights> higlights) {
        this.higlights = higlights;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.events> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.events> events) {
        this.events = events;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.new_blogs> getNew_blogs() {
        return new_blogs;
    }

    public void setNew_blogs(ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.new_blogs> new_blogs) {
        this.new_blogs = new_blogs;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.courses> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.courses> courses) {
        this.courses = courses;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.video_gallery> getVideo_gallery() {
        return video_gallery;
    }

    public void setVideo_gallery(ArrayList<com.neuronimbus.metropolis.Models.InsightScreen.video_gallery> video_gallery) {
        this.video_gallery = video_gallery;
    }
}
