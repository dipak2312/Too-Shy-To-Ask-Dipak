package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class OnBordingResponse {
    private String code;
    private String msg;
    ArrayList<OnboardingList> onboardingLists;

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

    public ArrayList<OnboardingList> getOnboardingLists() {
        return onboardingLists;
    }

    public void setOnboardingLists(ArrayList<OnboardingList> onboardingLists) {
        this.onboardingLists = onboardingLists;
    }
}
