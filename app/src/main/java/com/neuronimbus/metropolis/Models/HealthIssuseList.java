package com.neuronimbus.metropolis.Models;

public class HealthIssuseList {
    private String health_id;
    private String health_title;
    public Boolean isSelected = true;

    public HealthIssuseList(String health_id, String health_title, Boolean isSelected) {
        this.health_id = health_id;
        this.health_title = health_title;
        this.isSelected = isSelected;
    }


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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
