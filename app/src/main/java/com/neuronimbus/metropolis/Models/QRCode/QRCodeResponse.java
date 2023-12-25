package com.neuronimbus.metropolis.Models.QRCode;

import java.util.ArrayList;

public class QRCodeResponse {
    private String code;
    private String msg;
    private String user_id;
    private String ngo_name;
    private String android_qrcode_img;
    ArrayList<question>question;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getAndroid_qrcode_img() {
        return android_qrcode_img;
    }

    public void setAndroid_qrcode_img(String android_qrcode_img) {
        this.android_qrcode_img = android_qrcode_img;
    }

    public ArrayList<question> getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<question> question) {
        this.question = question;
    }
}
