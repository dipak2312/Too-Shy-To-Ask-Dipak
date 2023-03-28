package com.example.tooshytoask.Models.Courses.LMSQuiz;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Question implements Parcelable {
    String questionId;
    String courseId;
    String lessonId;
    String question;
    String description;
    String answerId;
    String questionImageUrl;
    ArrayList<Option>Option;

    protected Question(Parcel in) {
        questionId = in.readString();
        courseId = in.readString();
        lessonId = in.readString();
        question = in.readString();
        description = in.readString();
        answerId = in.readString();
        questionImageUrl = in.readString();
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

    public ArrayList<com.example.tooshytoask.Models.Courses.LMSQuiz.Option> getOption() {
        return Option;
    }

    public void setOption(ArrayList<com.example.tooshytoask.Models.Courses.LMSQuiz.Option> option) {
        Option = option;
    }
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
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

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(questionId);
        parcel.writeString(question);
        parcel.writeString(questionImageUrl);
        parcel.writeString(answerId);
        parcel.writeString(description);
        parcel.writeString(lessonId);
        parcel.writeString(courseId);

    }
}
