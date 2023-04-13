package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class SearchResponse {
    private String code;
    private String msg;
    ArrayList<storehouse_search>storehouse_search;
    ArrayList<video_search>video_search;
    ArrayList<course_search>course_search;
    ArrayList<blog_search>blog_search;
    ArrayList<event_search>event_search;

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

    public ArrayList<com.neuronimbus.metropolis.Models.storehouse_search> getStorehouse_search() {
        return storehouse_search;
    }

    public void setStorehouse_search(ArrayList<com.neuronimbus.metropolis.Models.storehouse_search> storehouse_search) {
        this.storehouse_search = storehouse_search;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.video_search> getVideo_search() {
        return video_search;
    }

    public void setVideo_search(ArrayList<com.neuronimbus.metropolis.Models.video_search> video_search) {
        this.video_search = video_search;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.course_search> getCourse_search() {
        return course_search;
    }

    public void setCourse_search(ArrayList<com.neuronimbus.metropolis.Models.course_search> course_search) {
        this.course_search = course_search;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.blog_search> getBlog_search() {
        return blog_search;
    }

    public void setBlog_search(ArrayList<com.neuronimbus.metropolis.Models.blog_search> blog_search) {
        this.blog_search = blog_search;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.event_search> getEvent_search() {
        return event_search;
    }

    public void setEvent_search(ArrayList<com.neuronimbus.metropolis.Models.event_search> event_search) {
        this.event_search = event_search;
    }
}
