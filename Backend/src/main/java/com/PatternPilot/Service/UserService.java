package com.PatternPilot.Service;

import com.PatternPilot.Domain.User;
import com.PatternPilot.Exceptions.PPAuthException;

/**
 * @author Abhishek V S
 **/
public interface UserService {
    User registerUser(String username, String email, String password) throws PPAuthException;
    User validateUser(String email,String password) throws PPAuthException;
}
