package com.neuronimbus.metropolis.Models.Courses.LMSQuiz;


import java.util.ArrayList;

public class LMSQuizesponse {
    private String code;
    private String msg;
    ArrayList<Question> Question;

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

    public ArrayList<com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Question> getQuestion() {
        return Question;
    }

    public void setQuestion(ArrayList<com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Question> question) {
        Question = question;
    }
}
