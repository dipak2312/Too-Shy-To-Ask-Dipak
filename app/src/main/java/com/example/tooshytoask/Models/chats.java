package com.example.tooshytoask.Models;

public class chats {
    private String question;
    private String created_at;
    private String date;
    private String reply;
    private String type;

    public chats(String question, String created_at, String date, String reply, String type) {
        this.question = question;
        this.created_at = created_at;
        this.date = date;
        this.reply = reply;
        this.type = type;
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
}
