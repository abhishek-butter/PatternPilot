package com.PatternPilot.Domain;

/**
 * @author Abhishek V S
 **/
public class Problem {
    Integer problemId;
    Integer topicId;
    Integer userId;
    String problemName;
    Double confidenceScore;

    public Problem(Integer problemId, Integer topicId, Integer userId, Double confidenceScore, String problemName) {
        this.problemId = problemId;
        this.topicId = topicId;
        this.userId = userId;
        this.confidenceScore = confidenceScore;
        this.problemName = problemName;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
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

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}
