package com.example.tooshytoask.Models.Courses;


import java.util.ArrayList;

public class CoursesDetailResponse {
    private String code;
    private String msg;
    ArrayList<data> data;

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

    public ArrayList<com.example.tooshytoask.Models.Courses.data> getData() {
        return data;
    }

    public void setData(ArrayList<com.example.tooshytoask.Models.Courses.data> data) {
        this.data = data;
    }
}
