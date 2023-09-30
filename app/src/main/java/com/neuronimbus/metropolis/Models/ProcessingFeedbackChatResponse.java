package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class ProcessingFeedbackChatResponse {
    private String code;
    private String msg;
    ArrayList<ProcessingFeedback>ProcessingFeedback;

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

    public ArrayList<com.neuronimbus.metropolis.Models.ProcessingFeedback> getProcessingFeedback() {
        return ProcessingFeedback;
    }

    public void setProcessingFeedback(ArrayList<com.neuronimbus.metropolis.Models.ProcessingFeedback> processingFeedback) {
        ProcessingFeedback = processingFeedback;
    }
}
