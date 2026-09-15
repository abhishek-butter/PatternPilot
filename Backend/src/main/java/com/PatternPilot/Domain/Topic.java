package com.PatternPilot.Domain;

/**
 * @author Abhishek V S
 **/
public class Topic {
    Integer topicId;
    Integer userId;
    String topicName;
    Double confidenceScore;

    public Topic(Integer topicId, Integer userId, String topicName, Double confidenceScore) {
        this.topicId = topicId;
        this.userId = userId;
        this.topicName = topicName;
        this.confidenceScore = confidenceScore;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}
