package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class GameScoreResponse {
    private String code;
    private String msg;
    private String gametime;
    private String usergametime;


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


    public String getGametime() {
        return gametime;
    }

    public void setGametime(String gametime) {
        this.gametime = gametime;
    }

    public String getUsergametime() {
        return usergametime;
    }

    public void setUsergametime(String usergametime) {
        this.usergametime = usergametime;
    }
}
