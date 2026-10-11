package com.PatternPilot.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
/**
 * @author Abhishek V S
 **/
public class LoginRequest {

    //ACT's as the Data Transfer Object with inbuilt validations thorugh springboot validation package


    @NotBlank(message="email is required")
    @Email(message="must be a valid email format")
    private String email;

    @NotBlank(message="password is required")
    private String passsword;

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
