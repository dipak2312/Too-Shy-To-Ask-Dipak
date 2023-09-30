package com.neuronimbus.metropolis.AuthModels;

public class AskFeedbackAuthModel {
    private String user_id;
    private String mainfeedbackId;
    private String feedback_id;
    private String assistance_type;
    private String feedback_reply_id_main;
    private String feedbackid_type;
    private String reply;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMainfeedbackId() {
        return mainfeedbackId;
    }

    public void setMainfeedbackId(String mainfeedbackId) {
        this.mainfeedbackId = mainfeedbackId;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getAssistance_type() {
        return assistance_type;
    }

    public void setAssistance_type(String assistance_type) {
        this.assistance_type = assistance_type;
    }

    public String getFeedback_reply_id_main() {
        return feedback_reply_id_main;
    }

    public void setFeedback_reply_id_main(String feedback_reply_id_main) {
        this.feedback_reply_id_main = feedback_reply_id_main;
    }

    public String getFeedbackid_type() {
        return feedbackid_type;
    }

    public void setFeedbackid_type(String feedbackid_type) {
        this.feedbackid_type = feedbackid_type;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
