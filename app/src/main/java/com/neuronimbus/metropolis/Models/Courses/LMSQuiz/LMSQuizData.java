package com.neuronimbus.metropolis.Models.Courses.LMSQuiz;

public class LMSQuizData {
    private String id;
    private String option;

    public LMSQuizData(String id, String option) {
        this.id = id;
        this.option = option;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
