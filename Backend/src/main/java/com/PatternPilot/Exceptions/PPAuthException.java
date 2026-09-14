package com.PatternPilot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Abhishek V S
 **/
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PPAuthException extends RuntimeException {
    public PPAuthException(String message) {
        super(message);
    }
}
