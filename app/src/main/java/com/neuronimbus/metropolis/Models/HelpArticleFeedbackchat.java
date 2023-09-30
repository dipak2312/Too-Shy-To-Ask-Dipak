package com.neuronimbus.metropolis.Models;

public class HelpArticleFeedbackchat {
    private UserFeedback UserFeedback;
    private ExpertReply ExpertReply;

    public com.neuronimbus.metropolis.Models.UserFeedback getUserFeedback() {
        return UserFeedback;
    }

    public void setUserFeedback(com.neuronimbus.metropolis.Models.UserFeedback userFeedback) {
        UserFeedback = userFeedback;
    }

    public com.neuronimbus.metropolis.Models.ExpertReply getExpertReply() {
        return ExpertReply;
    }

    public void setExpertReply(com.neuronimbus.metropolis.Models.ExpertReply expertReply) {
        ExpertReply = expertReply;
    }
}
