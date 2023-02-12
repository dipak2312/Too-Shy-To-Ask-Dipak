package com.example.tooshytoask.AuthModels;

import java.util.ArrayList;

public class SaveHealthCateAuthModel {
    private String user_id;
    private String healthId;
    ArrayList<String> health_id;




    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public ArrayList<String> getHealth_id() {
        return health_id;
    }

    public void setHealth_id(ArrayList<String> health_id) {
        this.health_id = health_id;
    }
}
