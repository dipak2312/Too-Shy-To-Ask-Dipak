package com.example.tooshytoask.Models;

public class OtpInResponse {
    private String msg;
    private String code;
    private String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private String profile_pic;

    public OtpInResponse(String msg, String code, String user_id, String first_name, String last_name, String phone, String gender, String dob, String profile_pic) {
        this.msg = msg;
        this.code = code;
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.profile_pic = profile_pic;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
