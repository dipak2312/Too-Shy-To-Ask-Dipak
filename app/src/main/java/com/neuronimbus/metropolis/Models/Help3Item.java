package com.neuronimbus.metropolis.Models;

public class Help3Item {
    int arrow_img;
    String question;

    public Help3Item(int arrow_img, String question) {
        this.arrow_img = arrow_img;
        this.question = question;
    }

    public int getArrow_img() {
        return arrow_img;
    }

    public void setArrow_img(int arrow_img) {
        this.arrow_img = arrow_img;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
