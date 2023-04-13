package com.neuronimbus.metropolis.Models.UpdateProfile;

public class health_issues {
    private String health_id;
    private String health_title;
    private String isselcted;
    public Boolean isSelected =false;

    public String getHealth_id() {
        return health_id;
    }

    public void setHealth_id(String health_id) {
        this.health_id = health_id;
    }

    public String getHealth_title() {
        return health_title;
    }

    public void setHealth_title(String health_title) {
        this.health_title = health_title;
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
