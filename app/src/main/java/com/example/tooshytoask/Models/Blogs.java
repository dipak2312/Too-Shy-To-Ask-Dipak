package com.example.tooshytoask.Models;

public class Blogs {
    private String blogId;
    private String blogTitle;
    private String blogContent;
    private String blogShort;
    private String blogLink;
    private Integer blogCategory;

    public String getBlogId() {
        return blogId;
    }
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
    public String getBlogTitle() {
        return blogTitle;
    }
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
    public String getBlogContent() {
        return blogContent;
    }
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
    public String getBlogShort() {
        return blogShort;
    }
    public void setBlogShort(String blogShort) {
        this.blogShort = blogShort;
    }
    public String getBlogLink() {
        return blogLink;
    }
    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }
    public Integer getBlogCategory() {
        return blogCategory;
    }
    public void setBlogCategory(Integer blogCategory) {
        this.blogCategory = blogCategory;
    }
}
