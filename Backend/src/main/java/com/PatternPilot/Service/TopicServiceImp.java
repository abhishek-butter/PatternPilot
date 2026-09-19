package com.PatternPilot.Service;

import com.PatternPilot.Domain.Topic;
import com.PatternPilot.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
@author Abhishek V S

**/
@Service
@Transactional
public class TopicServiceImp implements TopicService{

    @Autowired
    TopicRepository topicRepository;


    @Override
    public Topic addTopic(Integer userId, String topicName, Double confidenceScore) {
        Integer topicId=topicRepository.create(userId, topicName, confidenceScore);
        return topicRepository.findById(topicId,userId);
    }

    @Override
    public Topic findTopicById(Integer topicId, Integer userId) {
        return topicRepository.findById(topicId, userId);
    }

    @Override
    public List<Topic> listalltopics(Integer userId) {
        return topicRepository.findAll(userId);
    }

    @Override
    public Topic findTopicByName(Integer userId, String name) {
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
