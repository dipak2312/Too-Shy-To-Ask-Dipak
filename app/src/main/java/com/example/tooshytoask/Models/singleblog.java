package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class singleblog {
    private String blog_id;
    private String blog_title;
    private String blog_content;
    private String blog_short;
    private String blog_link;
    private String blog_category;
    private String blog_img;
    private String blog_like;
    private String blog_helpfull_yes;
    private String blog_helpfull_no;
    private String blog_bookmarked;
    private String blog_liked;
    private String blog_readTime;
    private String blog_helpfull_status;
    ArrayList<comments>comments;

    public String getBlog_helpfull_status() {
        return blog_helpfull_status;
    }

    public void setBlog_helpfull_status(String blog_helpfull_status) {
        this.blog_helpfull_status = blog_helpfull_status;
    }


    public String getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_content() {
        return blog_content;
    }

    public void setBlog_content(String blog_content) {
        this.blog_content = blog_content;
    }

    public String getBlog_short() {
        return blog_short;
    }

    public void setBlog_short(String blog_short) {
        this.blog_short = blog_short;
    }

    public String getBlog_link() {
        return blog_link;
    }

    public void setBlog_link(String blog_link) {
        this.blog_link = blog_link;
    }

    public String getBlog_category() {
        return blog_category;
    }

    public void setBlog_category(String blog_category) {
        this.blog_category = blog_category;
    }

    public String getBlog_img() {
        return blog_img;
    }

    public void setBlog_img(String blog_img) {
        this.blog_img = blog_img;
    }

    public String getBlog_like() {
        return blog_like;
    }

    public void setBlog_like(String blog_like) {
        this.blog_like = blog_like;
    }

    public String getBlog_helpfull_yes() {
        return blog_helpfull_yes;
    }

    public void setBlog_helpfull_yes(String blog_helpfull_yes) {
        this.blog_helpfull_yes = blog_helpfull_yes;
    }

    public String getBlog_helpfull_no() {
        return blog_helpfull_no;
    }

    public void setBlog_helpfull_no(String blog_helpfull_no) {
        this.blog_helpfull_no = blog_helpfull_no;
    }

    public ArrayList<com.example.tooshytoask.Models.comments> getComments() {
        return comments;
    }

    public void setComments(ArrayList<com.example.tooshytoask.Models.comments> comments) {
        this.comments = comments;
    }

    public String getBlog_bookmarked() {
        return blog_bookmarked;
    }

    public void setBlog_bookmarked(String blog_bookmarked) {
        this.blog_bookmarked = blog_bookmarked;
    }

    public String getBlog_liked() {
        return blog_liked;
    }

    public void setBlog_liked(String blog_liked) {
        this.blog_liked = blog_liked;
    }

    public String getBlog_readTime() {
        return blog_readTime;
    }

    public void setBlog_readTime(String blog_readTime) {
        this.blog_readTime = blog_readTime;
    }
}
