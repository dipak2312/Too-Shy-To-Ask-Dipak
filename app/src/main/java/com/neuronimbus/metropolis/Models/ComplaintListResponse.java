package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class ComplaintListResponse {
    private String code;
    private String msg;
    ArrayList<ComplaintHistoryChat>ComplaintHistoryChat;

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

    public ArrayList<com.neuronimbus.metropolis.Models.ComplaintHistoryChat> getComplaintHistoryChat() {
        return ComplaintHistoryChat;
    }

    public void setComplaintHistoryChat(ArrayList<com.neuronimbus.metropolis.Models.ComplaintHistoryChat> complaintHistoryChat) {
        ComplaintHistoryChat = complaintHistoryChat;
    }
}
