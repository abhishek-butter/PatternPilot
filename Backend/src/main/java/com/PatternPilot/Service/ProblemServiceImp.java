package com.PatternPilot.Service;

import com.PatternPilot.Domain.Problem;
import com.PatternPilot.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Abhishek V S
 **/
@Service
@Transactional
public class ProblemServiceImp implements ProblemService{

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public Problem addProblem(Integer topicId, Integer userId, String problemName, Double confidenceScore) {
        Integer problemId=problemRepository.create(topicId,userId,problemName,confidenceScore);
        return problemRepository.findById(problemId,userId);

    }

    @Override
    public Problem findProblemById(Integer problemId, Integer userId) {
        return problemRepository.findById(problemId,userId);
    }

    @Override
    public List<Problem> listAllProblem(Integer userId, Integer topicId) {
        return problemRepository.findAll(topicId, userId);
    }

    @Override
    public Problem findProblemByName(Integer topicId, Integer userId, String name) {
        return problemRepository.findByName(topicId, userId, name);
    }

    @Override
    public Double findScore(Integer problemId, Integer userId) {
        return problemRepository.findScore(problemId,userId);
    }

    @Override
    public void updateProblem(Integer problemId, Integer userId, Problem problem) {
        problemRepository.update(problemId,userId,problem);

    }
}
