package com.neuronimbus.metropolis.AuthModels;

public class AskQuestionsAuthModel {
    private String user_id;
    private String reply;
    private String questionType;
    private String language;
    private String recordingDuration;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRecordingDuration() {
        return recordingDuration;
    }

    public void setRecordingDuration(String recordingDuration) {
        this.recordingDuration = recordingDuration;
    }
}
