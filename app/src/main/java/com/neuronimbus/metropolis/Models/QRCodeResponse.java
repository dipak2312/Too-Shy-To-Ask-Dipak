package com.neuronimbus.metropolis.Models;

public class QRCodeResponse {
    private String code;
    private String msg;
    private String user_id;
    private String ngo_name;
    private String android_qrcode_img;
    private String ios_qrcode_img;
    private String android_qrcode_url;
    private String ios_qrcode_url;
    private String android_scan_url;
    private String ios_scan_url;

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

    public String getIos_qrcode_img() {
        return ios_qrcode_img;
    }

    public void setIos_qrcode_img(String ios_qrcode_img) {
        this.ios_qrcode_img = ios_qrcode_img;
    }

    public String getAndroid_qrcode_url() {
        return android_qrcode_url;
    }

    public void setAndroid_qrcode_url(String android_qrcode_url) {
        this.android_qrcode_url = android_qrcode_url;
    }

    public String getIos_qrcode_url() {
        return ios_qrcode_url;
    }

    public void setIos_qrcode_url(String ios_qrcode_url) {
        this.ios_qrcode_url = ios_qrcode_url;
    }

    public String getAndroid_scan_url() {
        return android_scan_url;
    }

    public void setAndroid_scan_url(String android_scan_url) {
        this.android_scan_url = android_scan_url;
    }

    public String getIos_scan_url() {
        return ios_scan_url;
    }

    public void setIos_scan_url(String ios_scan_url) {
        this.ios_scan_url = ios_scan_url;
    }
}
