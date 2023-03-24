package com.example.tooshytoask.Models.Courses.Lesson;

import java.util.ArrayList;

public class data {
    private String id;
    private String courseid;
    private String title;
    private String description;
    private String image;
    private String video;
    private String timing;
    private String quiz;
    private String lesson_status;
    ArrayList<upcominglesson>upcominglesson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getLesson_status() {
        return lesson_status;
    }

    public void setLesson_status(String lesson_status) {
        this.lesson_status = lesson_status;
    }

    public ArrayList<com.example.tooshytoask.Models.Courses.Lesson.upcominglesson> getUpcominglesson() {
        return upcominglesson;
    }

    public void setUpcominglesson(ArrayList<com.example.tooshytoask.Models.Courses.Lesson.upcominglesson> upcominglesson) {
        this.upcominglesson = upcominglesson;
    }
}
