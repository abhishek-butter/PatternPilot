package com.PatternPilot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Abhishek V S
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PPResourceNotFound extends RuntimeException {
    public PPResourceNotFound(String message) {
        super(message);
    }
}
