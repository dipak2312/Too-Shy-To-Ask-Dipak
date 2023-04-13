package com.neuronimbus.metropolis.AuthModels;

public class BlogLikeAuthModel {
    private String user_id;
    private String post_id;
    private String type;
    private String ishelpfull;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIshelpfull() {
        return ishelpfull;
    }

    public void setIshelpfull(String ishelpfull) {
        this.ishelpfull = ishelpfull;
    }
}
