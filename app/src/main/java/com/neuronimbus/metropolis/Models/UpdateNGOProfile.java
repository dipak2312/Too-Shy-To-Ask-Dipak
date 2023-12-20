package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class UpdateNGOProfile {
    private String user_id;
    private String action;
    private String ngo_name;
    private String ngo_registration_no;
    private String email_id;
    private String phone;
    private String work_experience;
    private String organization_activities;
    private String country;
    private String state;
    private String city;
    private ArrayList<String>ngo_project_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getNgo_registration_no() {
        return ngo_registration_no;
    }

    public void setNgo_registration_no(String ngo_registration_no) {
        this.ngo_registration_no = ngo_registration_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }

    public String getOrganization_activities() {
        return organization_activities;
    }

    public void setOrganization_activities(String organization_activities) {
        this.organization_activities = organization_activities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<String> getNgo_project_name() {
        return ngo_project_name;
    }

    public void setNgo_project_name(ArrayList<String> ngo_project_name) {
        this.ngo_project_name = ngo_project_name;
    }
}
