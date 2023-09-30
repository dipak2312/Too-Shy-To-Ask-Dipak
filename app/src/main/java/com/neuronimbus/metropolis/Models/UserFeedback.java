package com.neuronimbus.metropolis.Models;

public class UserFeedback {
    private String assistanceType;
    private String feedbackDesc;
    private String feedbackDatetime;

    public String getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(String assistanceType) {
        this.assistanceType = assistanceType;
    }

    public String getFeedbackDesc() {
        return feedbackDesc;
    }

    public void setFeedbackDesc(String feedbackDesc) {
        this.feedbackDesc = feedbackDesc;
    }

    public String getFeedbackDatetime() {
        return feedbackDatetime;
    }

    public void setFeedbackDatetime(String feedbackDatetime) {
        this.feedbackDatetime = feedbackDatetime;
    }
}
