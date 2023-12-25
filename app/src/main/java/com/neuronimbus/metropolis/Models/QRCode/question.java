package com.neuronimbus.metropolis.Models.QRCode;

public class question {
    private String title;
    private String description;
    private boolean expandable;

    public question(String title, String description, boolean expandable) {
        this.title = title;
        this.description = description;
        this.expandable = expandable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
