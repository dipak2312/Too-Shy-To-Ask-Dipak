package com.example.tooshytoask.Models;

import com.example.tooshytoask.Models.UpdateProfile.profiledetails;

public class UserProfileResponse {
    private String code;
    private String msg;
    private String profile_percent;
    private String user_name;
    private String profile_pic;
    private profiledetails profiledetails;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getProfile_percent() {
        return profile_percent;
    }

    public void setProfile_percent(String profile_percent) {
        this.profile_percent = profile_percent;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public com.example.tooshytoask.Models.UpdateProfile.profiledetails getProfiledetails() {
        return profiledetails;
    }

    public void setProfiledetails(com.example.tooshytoask.Models.UpdateProfile.profiledetails profiledetails) {
        this.profiledetails = profiledetails;
    }
}
