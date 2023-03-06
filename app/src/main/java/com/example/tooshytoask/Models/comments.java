package com.example.tooshytoask.Models;

import java.util.ArrayList;

public class comments {
    private parent_comment parent_comment;
    ArrayList<child_comment>child_comment;

    public com.example.tooshytoask.Models.parent_comment getParent_comment() {
        return parent_comment;
    }

    public void setParent_comment(com.example.tooshytoask.Models.parent_comment parent_comment) {
        this.parent_comment = parent_comment;
    }

    public ArrayList<com.example.tooshytoask.Models.child_comment> getChild_comment() {
        return child_comment;
    }

    public void setChild_comment(ArrayList<com.example.tooshytoask.Models.child_comment> child_comment) {
        this.child_comment = child_comment;
    }
}
