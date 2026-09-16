package com.PatternPilot.Resource;

import com.PatternPilot.Domain.Topic;
import com.PatternPilot.Service.TopicService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static javax.swing.text.html.CSS.getAttribute;

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
    public ResponseEntity<Topic>

}
