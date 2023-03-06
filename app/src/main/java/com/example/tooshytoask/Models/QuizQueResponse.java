package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class QuizQueResponse {
    private String code;
    private String msg;
    ArrayList<Question>Question;

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

    public ArrayList<com.example.tooshytoask.Models.Question> getQuestion() {
        return Question;
    }

    public void setQuestion(ArrayList<com.example.tooshytoask.Models.Question> question) {
        Question = question;
    }
}
