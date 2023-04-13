package com.neuronimbus.metropolis.Models;

public class RecommendedBlogs {
    private String blog_id;
    private String blog_title;
    private String blog_content;
    private String blog_short;
    private String blog_link;
    private String blog_img;
    private Integer blog_category;
    private String blog_boomarked;

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

    public String getBlog_img() {
        return blog_img;
    }

    public void setBlog_img(String blog_img) {
        this.blog_img = blog_img;
    }

    public Integer getBlog_category() {
        return blog_category;
    }

    public void setBlog_category(Integer blog_category) {
        this.blog_category = blog_category;
    }

    public String getBlog_boomarked() {
        return blog_boomarked;
    }

    public void setBlog_boomarked(String blog_boomarked) {
        this.blog_boomarked = blog_boomarked;
    }
}
