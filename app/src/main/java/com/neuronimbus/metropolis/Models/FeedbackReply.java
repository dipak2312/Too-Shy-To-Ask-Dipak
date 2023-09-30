package com.neuronimbus.metropolis.Models;

public class FeedbackReply {
    private String MainFeedbackId;
    private String FeedbackReplyId;
    private String FeedbackReplyIdMain;
    private String feedbackIdType;
    private String AdminReplyAssistanceType;
    private String AdminReply;
    private String AdminReplyDateTime;

    public String getMainFeedbackId() {
        return MainFeedbackId;
    }

    public void setMainFeedbackId(String mainFeedbackId) {
        MainFeedbackId = mainFeedbackId;
    }

    public String getFeedbackReplyId() {
        return FeedbackReplyId;
    }

    public void setFeedbackReplyId(String feedbackReplyId) {
        FeedbackReplyId = feedbackReplyId;
    }

    public String getFeedbackReplyIdMain() {
        return FeedbackReplyIdMain;
    }

    public void setFeedbackReplyIdMain(String feedbackReplyIdMain) {
        FeedbackReplyIdMain = feedbackReplyIdMain;
    }

    public String getFeedbackIdType() {
        return feedbackIdType;
    }

    public void setFeedbackIdType(String feedbackIdType) {
        this.feedbackIdType = feedbackIdType;
    }

    public String getAdminReplyAssistanceType() {
        return AdminReplyAssistanceType;
    }

    public void setAdminReplyAssistanceType(String adminReplyAssistanceType) {
        AdminReplyAssistanceType = adminReplyAssistanceType;
    }

    public String getAdminReply() {
        return AdminReply;
    }

    public void setAdminReply(String adminReply) {
        AdminReply = adminReply;
    }

    public String getAdminReplyDateTime() {
        return AdminReplyDateTime;
    }

    public void setAdminReplyDateTime(String adminReplyDateTime) {
        AdminReplyDateTime = adminReplyDateTime;
    }
}
