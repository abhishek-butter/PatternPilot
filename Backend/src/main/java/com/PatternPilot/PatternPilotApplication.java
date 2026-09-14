package com.PatternPilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

/**
 * @author Abhishek V S
 **/
@SpringBootApplication
public class PatternPilotApplication {

    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        SpringApplication.run(PatternPilotApplication.class, args);
    }
}
