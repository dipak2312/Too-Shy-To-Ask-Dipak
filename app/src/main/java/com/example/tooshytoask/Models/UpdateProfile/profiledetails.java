package com.example.tooshytoask.Models.UpdateProfile;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class profiledetails {
    private profile profile;
    ArrayList<health_interest>health_interest;
    ArrayList<health_issues>health_issues;
    personal_info personalInfo;


    public com.example.tooshytoask.Models.UpdateProfile.profile getProfile() {
        return profile;
    }

    public void setProfile(com.example.tooshytoask.Models.UpdateProfile.profile profile) {
        this.profile = profile;
    }

    public ArrayList<com.example.tooshytoask.Models.UpdateProfile.health_interest> getHealth_interest() {
        return health_interest;
    }

    public void setHealth_interest(ArrayList<com.example.tooshytoask.Models.UpdateProfile.health_interest> health_interest) {
        this.health_interest = health_interest;
    }

    public ArrayList<com.example.tooshytoask.Models.UpdateProfile.health_issues> getHealth_issues() {
        return health_issues;
    }

    public void setHealth_issues(ArrayList<com.example.tooshytoask.Models.UpdateProfile.health_issues> health_issues) {
        this.health_issues = health_issues;
    }

    public personal_info getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(personal_info personalInfo) {
        this.personalInfo = personalInfo;
    }
}
