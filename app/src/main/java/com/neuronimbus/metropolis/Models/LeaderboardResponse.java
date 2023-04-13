package com.neuronimbus.metropolis.Models;

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

    public ArrayList<com.neuronimbus.metropolis.Models.TopThreeList> getTopThreeList() {
        return TopThreeList;
    }

    public void setTopThreeList(ArrayList<com.neuronimbus.metropolis.Models.TopThreeList> topThreeList) {
        TopThreeList = topThreeList;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.TopTenList> getTopTenList() {
        return TopTenList;
    }

    public void setTopTenList(ArrayList<com.neuronimbus.metropolis.Models.TopTenList> topTenList) {
        TopTenList = topTenList;
    }
}
