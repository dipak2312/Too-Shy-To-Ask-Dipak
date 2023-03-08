package com.example.tooshytoask.Models;

public class Bannerist {
    private String banner_id;
    private String banner_img;
    private String banner_screen;
    private String banner_blogid;
    public Boolean screen = true;

    public Bannerist(String banner_id, String banner_img, String banner_screen, String banner_blogid, Boolean screen) {
        this.banner_id = banner_id;
        this.banner_img = banner_img;
        this.banner_screen = banner_screen;
        this.banner_blogid = banner_blogid;
        this.screen = screen;
    }


    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getBanner_screen() {
        return banner_screen;
    }

    public void setBanner_screen(String banner_screen) {
        this.banner_screen = banner_screen;
    }

    public String getBanner_blogid() {
        return banner_blogid;
    }

    public void setBanner_blogid(String banner_blogid) {
        this.banner_blogid = banner_blogid;
    }

    public Boolean getScreen() {
        return screen;
    }

    public void setScreen(Boolean screen) {
        this.screen = screen;
    }
}
