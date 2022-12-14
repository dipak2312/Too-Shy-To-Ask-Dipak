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
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String USERID = "userid";
    public static final String USERPROFILE = "profile";
    public static final String TSTALOGINSTATUS="tstaloginstatus";
    public static final String LANGUAGE="language";


    String firstName,lastName,Email,Gender,Age,Phone,tstaLoginStatus,userId,userPhoto,language;

    public SPManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
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

    public String getAge() {
        Age = pref.getString(AGE, "");
        return Age;
    }

    public void setAge(String age) {
        editor.putString(AGE, age);
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
}
