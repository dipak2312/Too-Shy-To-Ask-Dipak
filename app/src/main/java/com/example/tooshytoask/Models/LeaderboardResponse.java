package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class LeaderboardResponse {

    private String code;
    private String msg;
    ArrayList<TopThreeList> TopThreeList;
    ArrayList<TopTenList>TopTenList;


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

    public ArrayList<com.example.tooshytoask.Models.TopThreeList> getTopThreeList() {
        return TopThreeList;
    }

    public void setTopThreeList(ArrayList<com.example.tooshytoask.Models.TopThreeList> topThreeList) {
        TopThreeList = topThreeList;
    }

    public ArrayList<com.example.tooshytoask.Models.TopTenList> getTopTenList() {
        return TopTenList;
    }

    public void setTopTenList(ArrayList<com.example.tooshytoask.Models.TopTenList> topTenList) {
        TopTenList = topTenList;
    }
}
