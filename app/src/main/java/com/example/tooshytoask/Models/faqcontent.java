package com.example.tooshytoask.Models;

public class faqcontent {
    private String id;
    private String title;
    private String content;
    private boolean expandable;

    public faqcontent(String id, String title, String content, boolean expandable) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.expandable = expandable;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
