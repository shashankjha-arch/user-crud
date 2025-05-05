package com.user.curd.user_crud.controller;

import com.user.curd.user_crud.dto.request.LoginRequest;
import com.user.curd.user_crud.dto.request.SignUpRequest;
import com.user.curd.user_crud.service.LoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        logger.info("Received sign-up request for email: {}", signUpRequest.getEmail());
        try {
            loginService.signUp(signUpRequest);
            logger.info("User registered successfully: {}", signUpRequest.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (IllegalArgumentException e) {
            logger.warn("Sign-up failed for email {}: {}", signUpRequest.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Received sign-in request for email: {}", loginRequest.getEmail());
        try {
            if (loginService.signin(loginRequest)) {
                logger.info("Sign-in successful for email: {}", loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.OK).body("SignIn successful.");
            } else {
                logger.warn("Sign-in failed: Incorrect credentials for email: {}", loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password.");
            }
        } catch (IllegalArgumentException e) {
            logger.warn("Sign-in error for email {}: {}", loginRequest.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/passwordChange")
    public ResponseEntity<String> forgetPassword(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Received password change request for email: {}", loginRequest.getEmail());
        try {
            loginService.changePassword(loginRequest);
            logger.info("Password change successful for email: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("Password change successful.");
        } catch (IllegalArgumentException e) {
            logger.warn("Password change failed for email {}: {}", loginRequest.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Received delete request for email: {}", loginRequest.getEmail());
        try {
            loginService.deleteUser(loginRequest);
            logger.info("User deleted successfully: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
        } catch (IllegalArgumentException e) {
            logger.warn("Delete user failed for email {}: {}", loginRequest.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public String testSetup() {
        logger.info("Test endpoint called");
        return "Set up successful";
    }
}
