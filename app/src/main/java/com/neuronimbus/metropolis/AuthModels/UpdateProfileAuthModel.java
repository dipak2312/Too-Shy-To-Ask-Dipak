package com.neuronimbus.metropolis.AuthModels;

import java.util.ArrayList;

public class UpdateProfileAuthModel {
    private String user_id;
    private String action;
    private String first_name;
    private String last_name;
    private String email_id;
    private String phone;
    private String gender;
    private String dob;
    private String bloodgroup;
    private String height;
    private String weight;
    private String country;
    private String state;
    private String city;
    private String language;
    private String image;
    private String token;
    ArrayList<String> healthissue_id=new ArrayList<>();
    ArrayList<String> health_id=new ArrayList<>();


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

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getHealthissue_id() {
        return healthissue_id;
    }

    public void setHealthissue_id(ArrayList<String> healthissue_id) {
        this.healthissue_id = healthissue_id;
    }

    public ArrayList<String> getHealth_id() {
        return health_id;
    }

    public void setHealth_id(ArrayList<String> health_id) {
        this.health_id = health_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
