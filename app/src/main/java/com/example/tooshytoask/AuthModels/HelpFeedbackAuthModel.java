package com.example.tooshytoask.AuthModels;

public class HelpFeedbackAuthModel {
    private String user_id;
    private String helpcontent_id;
    private String feedback;
    private String feedbacktype;
    private String feedbackreason;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHelpcontent_id() {
        return helpcontent_id;
    }

    public void setHelpcontent_id(String helpcontent_id) {
        this.helpcontent_id = helpcontent_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedbacktype() {
        return feedbacktype;
    }

    public void setFeedbacktype(String feedbacktype) {
        this.feedbacktype = feedbacktype;
    }

    public String getFeedbackreason() {
        return feedbackreason;
    }

    public void setFeedbackreason(String feedbackreason) {
        this.feedbackreason = feedbackreason;
    }
}
