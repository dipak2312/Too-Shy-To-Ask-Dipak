package com.neuronimbus.metropolis.Models;

public class dataNotification {
    private String module_name;
    public Boolean status;
    private String manage_id;

    public dataNotification(String module_name, Boolean status, String manage_id) {
        this.module_name = module_name;
        this.status = status;
        this.manage_id = manage_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getManage_id() {
        return manage_id;
    }

    public void setManage_id(String manage_id) {
        this.manage_id = manage_id;
    }
}
