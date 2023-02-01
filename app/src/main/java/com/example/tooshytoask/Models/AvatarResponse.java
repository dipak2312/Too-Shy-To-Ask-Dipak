package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class AvatarResponse {
    private String code;
    private String msg;
    ArrayList<avatarList>avatarList;

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

    public ArrayList<com.example.tooshytoask.Models.avatarList> getAvatarList() {
        return avatarList;
    }

    public void setAvatarList(ArrayList<com.example.tooshytoask.Models.avatarList> avatarList) {
        this.avatarList = avatarList;
    }
}
