package com.neuronimbus.metropolis.AuthModels;

public class FeedbackAuthModel {
    private String user_id;
    private String feedbackreason;
    private String assistanceType;
    private String feedbackImage;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFeedbackreason() {
        return feedbackreason;
    }

    public void setFeedbackreason(String feedbackreason) {
        this.feedbackreason = feedbackreason;
    }

    public String getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(String assistanceType) {
        this.assistanceType = assistanceType;
    }

    public String getFeedbackImage() {
        return feedbackImage;
    }

    public void setFeedbackImage(String feedbackImage) {
        this.feedbackImage = feedbackImage;
    }
}
