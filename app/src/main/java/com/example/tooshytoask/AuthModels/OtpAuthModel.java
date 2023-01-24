package com.example.tooshytoask.AuthModels;

public class OtpAuthModel {

    private String mobile_no;
    private CharSequence otp_no;


    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public CharSequence getOtp_no() {
        return otp_no;
    }

    public void setOtp_no(CharSequence otp_no) {
        this.otp_no = otp_no;
    }
}
