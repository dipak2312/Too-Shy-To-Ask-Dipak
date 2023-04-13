package com.neuronimbus.metropolis.AuthModels;

import java.util.ArrayList;

public class ManageNotificationListUpdateAuthModel {
    private String user_id;

    ArrayList<String>module_ids;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<String> getModule_ids() {
        return module_ids;
    }

    public void setModule_ids(ArrayList<String> module_ids) {
        this.module_ids = module_ids;
    }
}
