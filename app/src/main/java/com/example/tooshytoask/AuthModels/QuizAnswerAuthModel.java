package com.example.tooshytoask.AuthModels;

import com.example.tooshytoask.Models.QuizData;

import java.util.ArrayList;

public class QuizAnswerAuthModel {
    private String user_id;
    private String quiz_id;
    ArrayList<QuizData>QuizData;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public ArrayList<com.example.tooshytoask.Models.QuizData> getQuizData() {
        return QuizData;
    }

    public void setQuizData(ArrayList<com.example.tooshytoask.Models.QuizData> quizData) {
        QuizData = quizData;
    }
}
