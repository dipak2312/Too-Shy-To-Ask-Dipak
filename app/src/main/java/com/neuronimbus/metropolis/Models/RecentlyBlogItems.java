package com.neuronimbus.metropolis.Models;

public class RecentlyBlogItems {
    int blog_img, save_img;
    String blog_title;

    public RecentlyBlogItems(int blog_img, int save_img, String blog_title) {
        this.blog_img = blog_img;
        this.save_img = save_img;
        this.blog_title = blog_title;
    }

    public int getBlog_img() {
        return blog_img;
    }

    public void setBlog_img(int blog_img) {
        this.blog_img = blog_img;
    }

    public int getSave_img() {
        return save_img;
    }

    public void setSave_img(int save_img) {
        this.save_img = save_img;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }
}
