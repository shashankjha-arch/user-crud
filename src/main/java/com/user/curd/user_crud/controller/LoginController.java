package com.user.curd.user_crud.controller;

import com.user.curd.user_crud.dto.request.LoginRequest;
import com.user.curd.user_crud.dto.request.SignUpRequest;
import com.user.curd.user_crud.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            loginService.signUp(signUpRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            if(loginService.signin(loginRequest)){
                return ResponseEntity.status(HttpStatus.CREATED).body("SignIn successful.");
            }else{
                return ResponseEntity.status(HttpStatus.CREATED).body("Incorrect email or password.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/passwordChange")
    public ResponseEntity<String> forgetPassword(@Valid @RequestBody LoginRequest loginRequest ){
        try {
            loginService.changePassword(loginRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("password change successful.");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody LoginRequest loginRequest ){
        try {
            loginService.deleteUser(loginRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Deleted");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public String testSetup(){
        return "Set up successful";
    }
}