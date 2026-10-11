package com.PatternPilot.Service;

import com.PatternPilot.Domain.Problem;
import com.PatternPilot.Domain.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Abhishek V S
 **/

public interface ProblemService {
    Problem addProblem(Integer topicId,Integer userId, String problemName, Double confidenceScore);
    Problem findProblemById(Integer problemId, Integer userId);
    List<Problem> listAllProblem(Integer userId, Integer topicId);
    Problem findProblemByName(Integer topicId,Integer userId, String name);
    Double findScore(Integer problemId,Integer userId);
    void updateProblem(Integer problemId,Integer userId,Problem problem);


}
