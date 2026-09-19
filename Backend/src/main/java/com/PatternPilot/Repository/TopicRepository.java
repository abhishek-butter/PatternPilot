package com.PatternPilot.Repository;

import com.PatternPilot.Domain.Topic;

import java.util.List;

/**
 * @author Abhishek V S
 **/
public interface TopicRepository {
    Integer create(Integer userId, String topicName, Double confidenceScore);
    Topic findById(Integer topicId,Integer userId);
    List<Topic> findAll(Integer userId);
    Topic findByName(Integer userId,String name);
    Double findScore(Integer topicId,Integer userId);
    void update(Integer topicId,Integer userId, Topic topic);

}
