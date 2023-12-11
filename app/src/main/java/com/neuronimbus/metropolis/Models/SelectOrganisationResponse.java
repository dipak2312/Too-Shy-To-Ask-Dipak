package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class SelectOrganisationResponse {
    private String code;
    private String msg;
    private String IsMetropolisPartner;
    private ArrayList<ProjectList> ProjectList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsMetropolisPartner() {
        return IsMetropolisPartner;
    }

    public void setIsMetropolisPartner(String isMetropolisPartner) {
        IsMetropolisPartner = isMetropolisPartner;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.ProjectList> getProjectList() {
        return ProjectList;
    }

    public void setProjectList(ArrayList<com.neuronimbus.metropolis.Models.ProjectList> projectList) {
        ProjectList = projectList;
    }
}
