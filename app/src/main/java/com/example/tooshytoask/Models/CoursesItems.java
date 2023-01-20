package com.example.tooshytoask.Models;

public class CoursesItems {
    int blog_img, save_img;
    String courses_title, course_time, lessons;

    public CoursesItems(int blog_img, int save_img, String courses_title, String course_time, String lessons) {
        this.blog_img = blog_img;
        this.save_img = save_img;
        this.courses_title = courses_title;
        this.course_time = course_time;
        this.lessons = lessons;
    }

    public int getBlog_img() {
        return blog_img;
    }

    public void setBlog_img(int blog_img) {
        this.blog_img = blog_img;
    }

    public int getSave_img() {
        return save_img;
    }

    public void setSave_img(int save_img) {
        this.save_img = save_img;
    }

    public String getCourses_title() {
        return courses_title;
    }

    public void setCourses_title(String courses_title) {
        this.courses_title = courses_title;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public String getLessons() {
        return lessons;
    }

    public void setLessons(String lessons) {
        this.lessons = lessons;
    }
}
