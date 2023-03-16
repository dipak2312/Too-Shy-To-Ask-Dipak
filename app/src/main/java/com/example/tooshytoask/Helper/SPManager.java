package com.example.tooshytoask.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SPManager {

    public static final String PREF_NAME = "TSTA";
    int PRIVATE_MODE = 0;
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String DOB = "dob";
    public static final String PHONE = "phone";
    public static final String USERID = "userid";
    public static final String USERPROFILE = "profile";
    public static final String TSTALOGINSTATUS="tstaloginstatus";

    public static final String TSTAGUESTLOGINSTATUS="tstaguestloginstatus";
    public static final String LANGUAGE="language";
    public static final String USER="user";
    public static final String COUNTRY="country";
    public static final String STATE="state";
    public static final String CITY="city";
    public static final String HEALTHCATEGORY="healthcategory";
    public static final String HEALTHISSUES="healthissues";
    public static final String BLOODGROUP="bloodgroup";
    public static final String HEIGHT="height";
    public static final String WEIGHT="weight";


    String firstName;
    String lastName;
    String Email;
    String Gender;
    String dob;
    String Phone;
    String tstaLoginStatus;
    String tstaguestLoginStatus;
    String userId;
    String userPhoto;
    String language;
    String user;
    String country;
    String state;
    String city;
    String healthcategory;
    String healthissues;
    String bloodgroup;
    String height;
    String weight;



    public String getUser() {
        user = pref.getString(USER, "");
        return user;
    }

    public void setUser(String user) {
        editor.putString(USER, user);
        editor.commit();
    }

    public SPManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getCountry() {
        country = pref.getString(COUNTRY, "");
        return country;
    }

    public void setCountry(String country) {
        editor.putString(COUNTRY, country);
        editor.commit();
    }

    public String getState() {
        state = pref.getString(STATE, "");
        return state;
    }

    public void setState(String state) {
        editor.putString(STATE, state);
        editor.commit();
    }

    public String getCity() {
        city = pref.getString(CITY, "");
        return city;
    }

    public void setCity(String city) {
        editor.putString(CITY, city);
        editor.commit();
    }

    public String getFirstName() {
        firstName = pref.getString(FIRSTNAME, "");
        return firstName;
    }

    public void setFirstName(String firstName) {
        editor.putString(FIRSTNAME, firstName);
        editor.commit();
    }

    public String getLastName() {
        lastName = pref.getString(LASTNAME, "");
        return lastName;
    }

    public void setLastName(String lastName) {
        editor.putString(LASTNAME, lastName);
        editor.commit();
    }

    public String getEmail() {
        Email = pref.getString(EMAIL, "");
        return Email;
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public String getGender() {
        Gender = pref.getString(GENDER, "");
        return Gender;
    }

    public void setGender(String gender) {
        editor.putString(GENDER, gender);
        editor.commit();
    }
    public String getDob() {
        dob = pref.getString(DOB, "");
        return dob;
    }

    public void setDob(String dob) {
        editor.putString(DOB, dob);
        editor.commit();
    }

    public String getPhone() {
        Phone = pref.getString(PHONE, "");
        return Phone;
    }

    public void setPhone(String phone) {
        editor.putString(PHONE, phone);
        editor.commit();
    }

    public String getTstaLoginStatus() {
        tstaLoginStatus = pref.getString(TSTALOGINSTATUS, "false");
        return tstaLoginStatus;
    }

    public void setTstaLoginStatus(String tstaLoginStatus) {
        editor.putString(TSTALOGINSTATUS, tstaLoginStatus);
        editor.commit();
    }

    public String getTstaguestLoginStatus() {
        tstaguestLoginStatus = pref.getString(TSTAGUESTLOGINSTATUS, "false");
        return tstaguestLoginStatus;
    }

    public void setTstaguestLoginStatus(String tstaguestLoginStatus) {
        editor.putString(TSTAGUESTLOGINSTATUS, tstaguestLoginStatus);
        editor.commit();
    }

    public String getUserId() {
        userId = pref.getString(USERID, "");
        return userId;
    }

    public void setUserId(String userId) {
        editor.putString(USERID, userId);
        editor.commit();
    }

    public String getUserPhoto() {
        userPhoto = pref.getString(USERPROFILE, "");
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        editor.putString(USERPROFILE, userPhoto);
        editor.commit();
    }

    public String getLanguage() {
        language = pref.getString(LANGUAGE, "");
        return language;
    }

    public void setLanguage(String language) {
        editor.putString(LANGUAGE, language);
        editor.commit();
    }

    public String getHealthcategory() {
        healthcategory = pref.getString(HEALTHCATEGORY, "");
        return healthcategory;
    }

    public void setHealthcategory(String healthcategory) {
        editor.putString(HEALTHCATEGORY, healthcategory);
        editor.commit();
    }

    public String getHealthissues() {
        healthcategory = pref.getString(HEALTHISSUES, "");
        return healthissues;
    }

    public void setHealthissues(String healthissues) {
        editor.putString(HEALTHISSUES, healthissues);
        editor.commit();
    }

    public String getBloodgroup() {
        bloodgroup = pref.getString(BLOODGROUP, "");
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        editor.putString(BLOODGROUP, bloodgroup);
        editor.commit();
    }

    public String getHeight() {
        height = pref.getString(HEIGHT, "");
        return height;
    }

    public void setHeight(String height) {
        editor.putString(HEIGHT, height);
        editor.commit();
    }

    public String getWeight() {
        weight = pref.getString(WEIGHT, "");
        return weight;
    }

    public void setWeight(String weight) {
        editor.putString(WEIGHT, weight);
        editor.commit();
    }
}
