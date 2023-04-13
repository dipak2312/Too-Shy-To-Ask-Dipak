package com.neuronimbus.metropolis.Models;

public class child_comment {
    private String comment_ID;
    private String comment_author;
    private String comment_author_email;
    private String comment_date;
    private String comment_content;
    private String comment_parent;

    public String getComment_ID() {
        return comment_ID;
    }

    public void setComment_ID(String comment_ID) {
        this.comment_ID = comment_ID;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_author_email() {
        return comment_author_email;
    }

    public void setComment_author_email(String comment_author_email) {
        this.comment_author_email = comment_author_email;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_parent() {
        return comment_parent;
    }

    public void setComment_parent(String comment_parent) {
        this.comment_parent = comment_parent;
    }
}
