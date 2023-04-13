package com.neuronimbus.metropolis.Models.Courses;

import java.util.ArrayList;

public class data {
    private String id;
    private String title;
    private String description;
    private String image;
    private String videolink;
    private String timing;
    private String currentcourse;
    private String total_lesson;
    ArrayList<lesson>lesson;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getCurrentcourse() {
        return currentcourse;
    }

    public void setCurrentcourse(String currentcourse) {
        this.currentcourse = currentcourse;
    }

    public String getTotal_lesson() {
        return total_lesson;
    }

    public void setTotal_lesson(String total_lesson) {
        this.total_lesson = total_lesson;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.Courses.lesson> getLesson() {
        return lesson;
    }

    public void setLesson(ArrayList<com.neuronimbus.metropolis.Models.Courses.lesson> lesson) {
        this.lesson = lesson;
    }
}
