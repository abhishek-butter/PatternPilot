package com.PatternPilot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Abhishek V S
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PPBadRequestException extends RuntimeException {
    public PPBadRequestException(String message) {
        super(message);
    }
}
