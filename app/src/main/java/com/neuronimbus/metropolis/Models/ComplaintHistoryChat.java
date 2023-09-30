package com.neuronimbus.metropolis.Models;

public class ComplaintHistoryChat {
    private String complaintform_id;
    private String topic;
    private String subject;
    private String description;
    private String reply;
    private String complaintform_img;
    private boolean expandable;

    public ComplaintHistoryChat(String complaintform_id, String topic, String subject, String description, String reply, String complaintform_img, boolean expandable) {
        this.complaintform_id = complaintform_id;
        this.topic = topic;
        this.subject = subject;
        this.description = description;
        this.reply = reply;
        this.complaintform_img = complaintform_img;
        this.expandable = expandable;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getComplaintform_id() {
        return complaintform_id;
    }

    public void setComplaintform_id(String complaintform_id) {
        this.complaintform_id = complaintform_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getComplaintform_img() {
        return complaintform_img;
    }

    public void setComplaintform_img(String complaintform_img) {
        this.complaintform_img = complaintform_img;
    }
}
