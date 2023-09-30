package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class FeedbackListResponse {
    private String code;
    private String msg;
    ArrayList<RecentFeedback> RecentFeedback;
    ArrayList<OldestFeedback> OldestFeedback;

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

    public ArrayList<com.neuronimbus.metropolis.Models.RecentFeedback> getRecentFeedback() {
        return RecentFeedback;
    }

    public void setRecentFeedback(ArrayList<com.neuronimbus.metropolis.Models.RecentFeedback> recentFeedback) {
        RecentFeedback = recentFeedback;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.OldestFeedback> getOldestFeedback() {
        return OldestFeedback;
    }

    public void setOldestFeedback(ArrayList<com.neuronimbus.metropolis.Models.OldestFeedback> oldestFeedback) {
        OldestFeedback = oldestFeedback;
    }
}

