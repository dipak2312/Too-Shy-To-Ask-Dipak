package com.neuronimbus.metropolis.Models;

import java.util.ArrayList;

public class resolvedFeedback {
    private String AssistanceType;
    private String FeedbackImg;
    private String FeedbackDesc;
    private String FeedbackDate;
    ArrayList<FeedbackChating>FeedbackChating;
    ArrayList<HelpArticleFeedbackChating>HelpArticleFeedbackChating;

    public ArrayList<com.neuronimbus.metropolis.Models.HelpArticleFeedbackChating> getHelpArticleFeedbackChating() {
        return HelpArticleFeedbackChating;
    }

    public void setHelpArticleFeedbackChating(ArrayList<com.neuronimbus.metropolis.Models.HelpArticleFeedbackChating> helpArticleFeedbackChating) {
        HelpArticleFeedbackChating = helpArticleFeedbackChating;
    }

    public String getAssistanceType() {
        return AssistanceType;
    }

    public void setAssistanceType(String assistanceType) {
        AssistanceType = assistanceType;
    }

    public String getFeedbackImg() {
        return FeedbackImg;
    }

    public void setFeedbackImg(String feedbackImg) {
        FeedbackImg = feedbackImg;
    }

    public String getFeedbackDesc() {
        return FeedbackDesc;
    }

    public void setFeedbackDesc(String feedbackDesc) {
        FeedbackDesc = feedbackDesc;
    }

    public String getFeedbackDate() {
        return FeedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        FeedbackDate = feedbackDate;
    }

    public ArrayList<com.neuronimbus.metropolis.Models.FeedbackChating> getFeedbackChating() {
        return FeedbackChating;
    }

    public void setFeedbackChating(ArrayList<com.neuronimbus.metropolis.Models.FeedbackChating> feedbackChating) {
        FeedbackChating = feedbackChating;
    }
}
