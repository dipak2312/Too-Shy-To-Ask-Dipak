package com.neuronimbus.metropolis.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Question implements Parcelable {
     String quizId;
     String questionId;
     String question;
     String description;
     String answerId;
     String questionImageUrl;
    ArrayList<Option>Option;

    protected Question(Parcel in) {
        quizId = in.readString();
        questionId = in.readString();
        question = in.readString();
        description = in.readString();
        answerId = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.Option> getOption() {
        return Option;
    }

    public void setOption(ArrayList<com.neuronimbus.metropolis.Models.Option> option) {
        Option = option;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(questionId);
        parcel.writeString(question);
        parcel.writeString(questionImageUrl);
        parcel.writeString(answerId);
        parcel.writeString(description);
        parcel.writeString(quizId);
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }
}
