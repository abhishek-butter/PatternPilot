package com.PatternPilot;

import com.PatternPilot.Filter.AuthFilter;
import com.PatternPilot.Filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import jakarta.servlet.FilterRegistration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 * @author Abhishek V S
 **/
@Configuration
@SpringBootApplication
public class PatternPilotApplication {

    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        SpringApplication.run(PatternPilotApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
        FilterRegistrationBean<AuthFilter> registrationBean=new FilterRegistrationBean<>();
        AuthFilter authFilter=new AuthFilter();

        registrationBean.setFilter(authFilter);

        registrationBean.addUrlPatterns("/topic/*");

        return registrationBean;

    }


}



