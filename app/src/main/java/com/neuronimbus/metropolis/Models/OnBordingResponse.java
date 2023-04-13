package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class OnBordingResponse {
    private String code;
    private String msg;
    ArrayList<OnboardingList> OnboardingList;

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
        return OnboardingList;
    }

    public void setOnboardingLists(ArrayList<OnboardingList> onboardingLists) {
        this.OnboardingList = onboardingLists;
    }
}
