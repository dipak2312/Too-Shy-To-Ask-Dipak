package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class SelectOrganisationAuthModel {
    private String user_id;
    ArrayList<String> ngoproject_id;
    private String others_desc;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<String> getNgoproject_id() {
        return ngoproject_id;
    }

    public void setNgoproject_id(ArrayList<String> ngoproject_id) {
        this.ngoproject_id = ngoproject_id;
    }

    public String getOthers_desc() {
        return others_desc;
    }

    public void setOthers_desc(String others_desc) {
        this.others_desc = others_desc;
    }
}
