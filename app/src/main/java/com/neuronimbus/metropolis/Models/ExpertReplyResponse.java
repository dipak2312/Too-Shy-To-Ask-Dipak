package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class ExpertReplyResponse {
    private String code;
    private String msg;
    ArrayList<chats>chats;

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

    public ArrayList<com.neuronimbus.metropolis.Models.chats> getChats() {
        return chats;
    }

    public void setChats(ArrayList<com.neuronimbus.metropolis.Models.chats> chats) {
        this.chats = chats;
    }
}
