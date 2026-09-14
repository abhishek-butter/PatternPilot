package com.PatternPilot.Resource;

import com.PatternPilot.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.PatternPilot.Domain.User;
import com.PatternPilot.Exceptions.PPAuthException;
import com.PatternPilot.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Abhishek V S
 **/
@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserServiceImp userService;


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,Object> userMap ) {
        try {
            if (userMap == null || userMap.get("email") == null || userMap.get("password") == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Email and password are required");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            
            String email=(String) userMap.get("email");
            String password=(String) userMap.get("password");
            User user=userService.validateUser(email,password);

            return new ResponseEntity<>(generateJWTTokens(user), HttpStatus.OK);
        } catch (PPAuthException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody Map<String,Object> userMap){
        try {
            if (userMap == null || userMap.get("username") == null || userMap.get("email") == null || userMap.get("password") == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Username, email and password are required");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            
            String username=(String) userMap.get("username");
            String email=(String) userMap.get("email");
            String password=(String) userMap.get("password");
            User user=userService.registerUser(username,email,password);
            return new ResponseEntity<>(generateJWTTokens(user),HttpStatus.CREATED);
        } catch (PPAuthException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, String> generateJWTTokens(User user) {
        long timestamp=System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.API_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp+Constants.Token_validity))
                .claim("userId",user.getUserId())
                .claim("email",user.getEmail())
                .claim("password",user.getPassword())
                .compact();
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
