package com.PatternPilot.Service;

import com.PatternPilot.Domain.User;
import com.PatternPilot.Exceptions.PPAuthException;
import com.PatternPilot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Abhishek V S
 */
@Service
@Transactional
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(String username, String email, String password) throws PPAuthException {
        return userRepository.findById(userRepository.createId(username,email,password));
    }

    @Override
    public User validateUser(String email, String password) throws PPAuthException {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
