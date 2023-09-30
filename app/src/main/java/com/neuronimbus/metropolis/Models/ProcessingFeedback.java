package com.neuronimbus.metropolis.Models;

public class ProcessingFeedback {
    private String assistance_type;
    private String feedback_desc;
    private String feedback_img;
    private String feedback_datetime;
    private String type;
    private String MainFeedbackId;
    private String FeedbackReplyId;
    private String FeedbackReplyIdMain;
    private String feedbackIdType;
    private String AdminReplyAssistanceType;
    private String AdminReply;
    private String AdminReplyDateTime;
    public Boolean isReply = true;

    public ProcessingFeedback(String assistance_type, String feedback_desc, String feedback_img, String feedback_datetime, String type, String mainFeedbackId, String feedbackReplyId, String feedbackReplyIdMain, String feedbackIdType, String adminReplyAssistanceType, String adminReply, String adminReplyDateTime, Boolean isReply) {
        this.assistance_type = assistance_type;
        this.feedback_desc = feedback_desc;
        this.feedback_img = feedback_img;
        this.feedback_datetime = feedback_datetime;
        this.type = type;
        MainFeedbackId = mainFeedbackId;
        FeedbackReplyId = feedbackReplyId;
        FeedbackReplyIdMain = feedbackReplyIdMain;
        this.feedbackIdType = feedbackIdType;
        AdminReplyAssistanceType = adminReplyAssistanceType;
        AdminReply = adminReply;
        AdminReplyDateTime = adminReplyDateTime;
        this.isReply = isReply;
    }

    public String getAssistance_type() {
        return assistance_type;
    }

    public void setAssistance_type(String assistance_type) {
        this.assistance_type = assistance_type;
    }

    public String getFeedback_desc() {
        return feedback_desc;
    }

    public void setFeedback_desc(String feedback_desc) {
        this.feedback_desc = feedback_desc;
    }

    public String getFeedback_img() {
        return feedback_img;
    }

    public void setFeedback_img(String feedback_img) {
        this.feedback_img = feedback_img;
    }

    public String getFeedback_datetime() {
        return feedback_datetime;
    }

    public void setFeedback_datetime(String feedback_datetime) {
        this.feedback_datetime = feedback_datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
    //private FeedbackReply feedbackReply;
    //ArrayList<HelpArticleFeedbackchat>HelpArticleFeedbackchat;


}
