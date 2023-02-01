package com.example.tooshytoask.AuthModels;

import java.util.ArrayList;

public class SaveHealthCateAuthModel {
    private String userId;
    private ArrayList<String> healthId;


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public ArrayList<String> getHealthId() {
        return healthId;
    }
    public void setHealthId(ArrayList<String> healthId) {
        this.healthId = healthId;
    }
}
