package com.example.tooshytoask.Models.Help;

import java.util.ArrayList;

public class helpsubcategory {
    private String id;
    private String title;
    private ArrayList<content> content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<com.example.tooshytoask.Models.Help.content> getContent() {
        return content;
    }

    public void setContent(ArrayList<com.example.tooshytoask.Models.Help.content> content) {
        this.content = content;
    }
}
