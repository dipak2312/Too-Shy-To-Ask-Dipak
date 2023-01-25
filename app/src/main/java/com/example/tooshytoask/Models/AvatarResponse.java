package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class AvatarResponse {
    private String code;
    private String msg;
    ArrayList<avatarList>avatarLists;

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

    public ArrayList<avatarList> getAvatarLists() {
        return avatarLists;
    }

    public void setAvatarLists(ArrayList<avatarList> avatarLists) {
        this.avatarLists = avatarLists;
    }
}
