package com.neuronimbus.metropolis.Models;

public class RecentFeedback {
    private String feedback_id;
    private String assistance_type;
    private String feedback_img;
    private String feedback_desc;
    private String reply_to_admin;
    private String created_at;
    private String updated_at;

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

    public String getFeedback_img() {
        return feedback_img;
    }

    public void setFeedback_img(String feedback_img) {
        this.feedback_img = feedback_img;
    }

    public String getFeedback_desc() {
        return feedback_desc;
    }

    public void setFeedback_desc(String feedback_desc) {
        this.feedback_desc = feedback_desc;
    }

    public String getReply_to_admin() {
        return reply_to_admin;
    }

    public void setReply_to_admin(String reply_to_admin) {
        this.reply_to_admin = reply_to_admin;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

