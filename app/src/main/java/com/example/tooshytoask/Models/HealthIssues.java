package com.example.tooshytoask.Models;

public class HealthIssues {
    String health_btn, health_btn2;

    public HealthIssues(String health_btn, String health_btn2) {
        this.health_btn = health_btn;
        this.health_btn2 = health_btn2;
    }


    public String getHealth_btn() {
        return health_btn;
    }

    public void setHealth_btn(String health_btn) {
        this.health_btn = health_btn;
    }

    public String getHealth_btn2() {
        return health_btn2;
    }

    public void setHealth_btn2(String health_btn2) {
        this.health_btn2 = health_btn2;
    }
}
