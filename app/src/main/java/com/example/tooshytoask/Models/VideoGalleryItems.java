package com.example.tooshytoask.Models;

public class VideoGalleryItems {
    int blog_img, save_img, play_video;
    String blog_title;

    public VideoGalleryItems(int blog_img, int save_img, int play_video, String blog_title) {
        this.blog_img = blog_img;
        this.save_img = save_img;
        this.play_video = play_video;
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

    public int getPlay_video() {
        return play_video;
    }

    public void setPlay_video(int play_video) {
        this.play_video = play_video;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }
}
