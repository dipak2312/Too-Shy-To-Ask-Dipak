package com.neuronimbus.metropolis.Models;

public class OldFeedbackChattingResponse {
    private String code;
    private String msg;
    private resolvedFeedback resolvedFeedback;

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

    public com.neuronimbus.metropolis.Models.resolvedFeedback getResolvedFeedback() {
        return resolvedFeedback;
    }

    public void setResolvedFeedback(com.neuronimbus.metropolis.Models.resolvedFeedback resolvedFeedback) {
        this.resolvedFeedback = resolvedFeedback;
    }
}
