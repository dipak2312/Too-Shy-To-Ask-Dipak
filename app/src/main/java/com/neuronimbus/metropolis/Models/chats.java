package com.neuronimbus.metropolis.Models;

public class chats {
    private String question;
    private String created_at;
    private String date;
    private String reply;
    private String type;
    private String question_type;
    private String recordingDuration;
    private String profile_pic;
    public Boolean status;

    public chats(String question, String created_at, String date, String reply, String type,
                 String question_type, String recordingDuration, String profile_pic, Boolean status) {
        this.question = question;
        this.created_at = created_at;
        this.date = date;
        this.reply = reply;
        this.type = type;
        this.question_type = question_type;
        this.recordingDuration = recordingDuration;
        this.profile_pic = profile_pic;
        this.status = status;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getRecordingDuration() {
        return recordingDuration;
    }

    public void setRecordingDuration(String recordingDuration) {
        this.recordingDuration = recordingDuration;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
