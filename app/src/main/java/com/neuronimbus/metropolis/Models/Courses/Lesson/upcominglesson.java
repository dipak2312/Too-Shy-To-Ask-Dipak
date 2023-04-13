package com.neuronimbus.metropolis.Models.Courses.Lesson;

public class upcominglesson {
    private String sno;
    private String id;
    private String title;
    private String timing;
    private String quiz;
    private String lesson_status;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

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
}
