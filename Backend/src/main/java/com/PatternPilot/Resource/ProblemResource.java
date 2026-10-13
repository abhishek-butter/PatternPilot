package com.PatternPilot.Resource;

import com.PatternPilot.Domain.Problem;
import com.PatternPilot.Domain.Topic;
import com.PatternPilot.Service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abhishek V S
 **/
@RestController
@RequestMapping("/topics/{topicId}/problems")
public class ProblemResource {
    @Autowired
    ProblemService problemService;


    @GetMapping("")
    ResponseEntity<List<Problem>> getProblems(HttpServletRequest request, @PathVariable Integer topicId){
        Integer userId=(Integer)request.getAttribute("userId");
        List<Problem> l=problemService.listAllProblem(userId,topicId);
        return new ResponseEntity<>(l, HttpStatus.OK);

    }

    @GetMapping("/{problemId}")
    public ResponseEntity<Problem> getProblemById(HttpServletRequest request, @PathVariable Integer problemId){
        Integer userId=(Integer)request.getAttribute("userId");
        Problem p=problemService.findProblemById(problemId,userId);
        return ResponseEntity.ok(p);


    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Problem> getProblemByName(HttpServletRequest request,@PathVariable String name,@PathVariable Integer topicId){
        Integer userId=(Integer)request.getAttribute("userId");
        Problem P=problemService.findProblemByName(topicId,userId,name);
        return ResponseEntity.ok(P);

    }

    @PostMapping("")
    public ResponseEntity<Problem> addProblem(HttpServletRequest request,@RequestBody Map<String,Object> categoryMap,@PathVariable Integer topicId){
        Integer userId=(Integer)request.getAttribute("userId");
        Problem p=problemService.addProblem(topicId,userId,(String)categoryMap.get("problemName"),(Double)categoryMap.get("confidenceScore"));
        return new ResponseEntity<>(p,HttpStatus.CREATED);



    }
    @PutMapping("/{problemId}")
    public ResponseEntity<Map<String,Boolean>> updateProblem(HttpServletRequest request,@RequestBody Map<String,Object> newProblem,@PathVariable Integer problemId,@PathVariable Integer topicId){
        Integer userId=(Integer)request.getAttribute("userId");
        problemService.updateProblem(problemId,userId,new Problem(problemId,topicId,userId,(Double) newProblem.get("confidenceScore"),(String) newProblem.get("problemName")));
        Map<String,Boolean> map=new HashMap<>();
        map.put("Success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }



}
