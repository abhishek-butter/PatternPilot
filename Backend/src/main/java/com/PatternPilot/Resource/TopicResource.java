package com.PatternPilot.Resource;

import com.PatternPilot.Domain.Topic;
import com.PatternPilot.Service.TopicService;
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
@RequestMapping("/topic")
public class TopicResource {

    @Autowired
    TopicService topicService;

    @GetMapping("")
    public ResponseEntity<List<Topic>> getTopics(HttpServletRequest request){
        Integer userId=(Integer)request.getAttribute("userId");
        List<Topic> l=topicService.listalltopics(userId);
        return new ResponseEntity<>(l,HttpStatus.OK);


    }
    @GetMapping("/{topicId}")
    public ResponseEntity<Topic> getTopicById(HttpServletRequest request, @PathVariable Integer topicId){
        Integer userId=(Integer)request.getAttribute("userId");
        Topic t=topicService.findTopicById(topicId,userId);
        return ResponseEntity.ok(t);


    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Topic> getTopicByName(HttpServletRequest request,@PathVariable String name){
        Integer userId=(Integer)request.getAttribute("userId");
        Topic t=topicService.findTopicByName(userId,name);
        return ResponseEntity.ok(t);

    }

    @PostMapping("")
    public ResponseEntity<Topic> addTopic(HttpServletRequest request,@RequestBody Map<String,Object> categoryMap){
        Integer userId=(Integer)request.getAttribute("userId");
        Topic t=topicService.addTopic(userId,(String)categoryMap.get("topicName"),(Double)categoryMap.get("confidenceScore"));
        return new ResponseEntity<>(t,HttpStatus.CREATED);



    }
    @PutMapping("/{topicId}")
    public ResponseEntity<Map<String,Boolean>> updateTopic(HttpServletRequest request,@RequestBody Map<String,Object> newTopic,@PathVariable Integer topicId){
        Integer userId=(Integer)request.getAttribute("userId");
        topicService.updateTopic(topicId,userId,new Topic(topicId,userId,(String) newTopic.get("topicName"),(Double) newTopic.get("confidenceScore")));
        Map<String,Boolean> map=new HashMap<>();
        map.put("Success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


}
