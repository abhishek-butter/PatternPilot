package com.PatternPilot.Service;

import com.PatternPilot.Domain.Topic;
import com.PatternPilot.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
@author Abhishek V S
**/public class TopicServiceImp implements TopicService{

    @Autowired
    TopicRepository topicRepository;


    @Override
    public Integer addTopic(Integer userId, String topicName, Double confidenceScore) {
        return topicRepository.create(userId, topicName, confidenceScore);
    }

    @Override
    public Topic findTopic(Integer topicId, Integer userId) {
        return topicRepository.findById(topicId, userId);
    }

    @Override
    public List<Topic> listalltopics(Integer userId) {
        return topicRepository.findAll(userId);
    }

    @Override
    public Topic findTopic(Integer userId, String name) {
       return topicRepository.findByName(userId, name);
    }

    @Override
    public Double findScore(Integer topicId, Integer userId) {
        return topicRepository.findScore(topicId, userId);
    }

    @Override
    public void updateTopic(Integer topicId, Integer userId, Topic topic) {
        topicRepository.update(topicId, userId, topic);

    }
}
