package com.example.tooshytoask.Models;

public class HealthIssues {
    String health_btn;
    public Boolean isSelected = false;

    public HealthIssues(String health_btn, Boolean isSelected) {
        this.health_btn = health_btn;
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getHealth_btn() {
        return health_btn;
    }

    public void setHealth_btn(String health_btn) {
        this.health_btn = health_btn;
    }

}
