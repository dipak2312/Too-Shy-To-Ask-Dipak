package com.neuronimbus.metropolis.Models;

public class NGOProfileResponse {
    private String code;
    private String msg;
    private String ngo_name;
    private String profile_pic;
    profiledetails profiledetails;

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

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public com.neuronimbus.metropolis.Models.profiledetails getProfiledetails() {
        return profiledetails;
    }

    public void setProfiledetails(com.neuronimbus.metropolis.Models.profiledetails profiledetails) {
        this.profiledetails = profiledetails;
    }
}
