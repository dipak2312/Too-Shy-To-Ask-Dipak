package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class comments {
    private parent_comment parent_comment;
    ArrayList<child_comment>child_comment;

    public com.neuronimbus.metropolis.Models.parent_comment getParent_comment() {
        return parent_comment;
    }

    public void setParent_comment(com.neuronimbus.metropolis.Models.parent_comment parent_comment) {
        this.parent_comment = parent_comment;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.child_comment> getChild_comment() {
        return child_comment;
    }

    public void setChild_comment(ArrayList<com.neuronimbus.metropolis.Models.child_comment> child_comment) {
        this.child_comment = child_comment;
    }
}
