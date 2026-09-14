package com.PatternPilot.Repository;

import com.PatternPilot.Domain.User;
import com.PatternPilot.Exceptions.*;

/**
 * @author Abhishek V S
 **/
public interface UserRepository {
        Integer createId(String username, String email, String password)throws PPAuthException;
        User findById(Integer userId)throws PPAuthException;
        User findByEmailAndPassword(String email, String password) throws PPAuthException;
        void updateUser(Integer userId, User updatedUser) throws PPAuthException;
        void deleteUser(Integer userId) throws PPAuthException;
}


