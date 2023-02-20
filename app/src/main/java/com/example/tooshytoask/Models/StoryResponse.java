package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class StoryResponse {
    private String code;
    private String msg;
    ArrayList<StoryDetails>StoryDetails;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<com.example.tooshytoask.Models.StoryDetails> getStoryDetails() {
        return StoryDetails;
    }

    public void setStoryDetails(ArrayList<com.example.tooshytoask.Models.StoryDetails> storyDetails) {
        StoryDetails = storyDetails;
    }
}
