package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class BookmarkResponse {
    private String code;
    private String msg;
    ArrayList<blog_bookmark>blog_bookmark;
    ArrayList<infostorehousebookmark>infostorehousebookmark;
    ArrayList<videobookmarks>videobookmarks;
    ArrayList<events>events;
    ArrayList<courses>courses;

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

    public ArrayList<com.neuronimbus.metropolis.Models.blog_bookmark> getBlog_bookmark() {
        return blog_bookmark;
    }

    public void setBlog_bookmark(ArrayList<com.neuronimbus.metropolis.Models.blog_bookmark> blog_bookmark) {
        this.blog_bookmark = blog_bookmark;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.infostorehousebookmark> getInfostorehousebookmark() {
        return infostorehousebookmark;
    }

    public void setInfostorehousebookmark(ArrayList<com.neuronimbus.metropolis.Models.infostorehousebookmark> infostorehousebookmark) {
        this.infostorehousebookmark = infostorehousebookmark;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.videobookmarks> getVideobookmarks() {
        return videobookmarks;
    }

    public void setVideobookmarks(ArrayList<com.neuronimbus.metropolis.Models.videobookmarks> videobookmarks) {
        this.videobookmarks = videobookmarks;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.events> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<com.neuronimbus.metropolis.Models.events> events) {
        this.events = events;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.courses> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<com.neuronimbus.metropolis.Models.courses> courses) {
        this.courses = courses;
    }
}
