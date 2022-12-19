package com.example.tooshytoask.Models;

public class SliderText {
    private String view_pager_title,view_pager_dec;

    public String getView_pager_title() {
        return view_pager_title;
    }

    public void setView_pager_title(String view_pager_title) {
        this.view_pager_title = view_pager_title;
    }

    public String getView_pager_dec() {
        return view_pager_dec;
    }

    public void setView_pager_dec(String view_pager_dec) {
        this.view_pager_dec = view_pager_dec;
    }
    public SliderText (String view_pager_title, String view_pager_dec){
        this.view_pager_title = view_pager_title;
        this.view_pager_dec = view_pager_dec;

    }



}
