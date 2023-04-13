package com.neuronimbus.metropolis.Models;

public class HelpCategory {
    int help_img;
    String help_text;

    public HelpCategory(int help_img, String help_text) {
        this.help_img = help_img;
        this.help_text =help_text;
    }

    public String getHelp_text() {
        return help_text;
    }

    public void setHelp_text(String help_text) {
        this.help_text = help_text;
    }

    public int getHelp_img() {
        return help_img;
    }

    public void setHelp_img(int help_img) {
        this.help_img = help_img;
    }
}
