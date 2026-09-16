package com.PatternPilot.Service;

import com.PatternPilot.Domain.Topic;

import java.util.List;

/**
 * @author Abhishek V S
 **/
public interface TopicService {
    Integer addTopic(Integer userId,String topicName,Double confidenceScore);
    Topic findTopic(Integer topicId, Integer userId);
    List<Topic> listalltopics(Integer userId);
    Topic findTopic(Integer userId,String name);
    Double findScore(Integer topicId,Integer userId);
    void updateTopic(Integer topicId,Integer userId,Topic topic);


}
