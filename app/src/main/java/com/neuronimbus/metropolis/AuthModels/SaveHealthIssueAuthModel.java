package com.neuronimbus.metropolis.AuthModels;

import java.util.ArrayList;

public class SaveHealthIssueAuthModel {

    private String user_id;
    ArrayList<String> healthissue_id;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<String> getHealthissue_id() {
        return healthissue_id;
    }

    public void setHealthissue_id(ArrayList<String> healthissue_id) {
        this.healthissue_id = healthissue_id;
    }
}
