package com.example.tooshytoask.AuthModels;

import java.util.ArrayList;

public class SaveHealthIssueAuthModel {

    private String userId;
    private ArrayList<String>healthissueId;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public ArrayList<String> getHealthissueId() {
        return healthissueId;
    }
    public void setHealthissueId(ArrayList<String>healthissueId) {
        this.healthissueId = healthissueId;
    }
}
