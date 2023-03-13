package com.example.tooshytoask.Models;

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

    public ArrayList<com.example.tooshytoask.Models.blog_bookmark> getBlog_bookmark() {
        return blog_bookmark;
    }

    public void setBlog_bookmark(ArrayList<com.example.tooshytoask.Models.blog_bookmark> blog_bookmark) {
        this.blog_bookmark = blog_bookmark;
    }

    public ArrayList<com.example.tooshytoask.Models.infostorehousebookmark> getInfostorehousebookmark() {
        return infostorehousebookmark;
    }

    public void setInfostorehousebookmark(ArrayList<com.example.tooshytoask.Models.infostorehousebookmark> infostorehousebookmark) {
        this.infostorehousebookmark = infostorehousebookmark;
    }

    public ArrayList<com.example.tooshytoask.Models.videobookmarks> getVideobookmarks() {
        return videobookmarks;
    }

    public void setVideobookmarks(ArrayList<com.example.tooshytoask.Models.videobookmarks> videobookmarks) {
        this.videobookmarks = videobookmarks;
    }

    public ArrayList<com.example.tooshytoask.Models.events> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<com.example.tooshytoask.Models.events> events) {
        this.events = events;
    }

    public ArrayList<com.example.tooshytoask.Models.courses> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<com.example.tooshytoask.Models.courses> courses) {
        this.courses = courses;
    }
}
