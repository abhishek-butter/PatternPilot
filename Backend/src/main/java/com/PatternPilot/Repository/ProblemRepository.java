package com.PatternPilot.Repository;

import com.PatternPilot.Domain.Problem;
import com.PatternPilot.Domain.Topic;

import java.util.List;

/**
 * @author Abhishek V S
 **/
public interface ProblemRepository {

    //solved primary key redundency by removign topicId from search and update queries parameters
    Integer create( Integer topicId,Integer userId, String problemName, Double confidenceScore);
    Problem findById(Integer problemId, Integer userId);
    List<Problem> findAll(Integer topicId, Integer userId);
    Problem findByName(Integer topicId,Integer userId,String problemName);
    Double findScore(Integer problemId,Integer userId);
    void update(Integer problemId,Integer userId, Problem problem);

}
