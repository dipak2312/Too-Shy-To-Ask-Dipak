package com.neuronimbus.metropolis.Models.UpdateProfile;

public class health_interest {
    private String id;
    private String title;
    private String slug;
    private String isselcted;
    public Boolean isSelected = false;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIsselcted() {
        return isselcted;
    }

    public void setIsselcted(String isselcted) {
        this.isselcted = isselcted;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
