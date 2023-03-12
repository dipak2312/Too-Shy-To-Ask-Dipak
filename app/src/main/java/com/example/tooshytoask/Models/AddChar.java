package com.example.tooshytoask.Models;

public class AddChar {
    private String charValue;
    boolean status;

    public AddChar(String charValue, boolean status) {
        this.charValue = charValue;
        this.status = status;
    }

    public String getCharValue() {
        return charValue;
    }

    public void setCharValue(String charValue) {
        this.charValue = charValue;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
