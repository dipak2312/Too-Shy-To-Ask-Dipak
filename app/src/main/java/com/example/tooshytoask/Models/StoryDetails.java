package com.example.tooshytoask.Models;

public class StoryDetails {
    private String story_id;
    private String story_title;
    private String story_img;
    private String story_video;
    private String story_date;
    private String story_link;
    private String like_cnt;
    private String share_cnt;
    private String liked;

    public String getStory_link() {
        return story_link;
    }

    public void setStory_link(String story_link) {
        this.story_link = story_link;
    }



    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getStory_img() {
        return story_img;
    }

    public void setStory_img(String story_img) {
        this.story_img = story_img;
    }

    public String getStory_video() {
        return story_video;
    }

    public void setStory_video(String story_video) {
        this.story_video = story_video;
    }

    public String getStory_date() {
        return story_date;
    }

    public void setStory_date(String story_date) {
        this.story_date = story_date;
    }

    public String getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(String like_cnt) {
        this.like_cnt = like_cnt;
    }

    public String getShare_cnt() {
        return share_cnt;
    }

    public void setShare_cnt(String share_cnt) {
        this.share_cnt = share_cnt;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }
}
