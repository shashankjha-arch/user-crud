package com.user.curd.user_crud.service;

import com.user.curd.user_crud.data.entity.UserDetailsEntity;
import com.user.curd.user_crud.data.repository.UserDetailsRepository;
import com.user.curd.user_crud.dto.request.LoginRequest;
import com.user.curd.user_crud.dto.request.SignUpRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserDetailsRepository userDetailsRepository;
    public void signUp(@Valid SignUpRequest signUpRequest) {
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        if (!isValidEmailFormat(signUpRequest.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (userDetailsRepository.findByEmail(signUpRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already registered.");
        }
        if (!isStrongPassword(signUpRequest.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain a mix of letters, numbers, and special characters.");
        }
        userDetailsEntity.setFirstName(signUpRequest.getFirstName());
        userDetailsEntity.setLastName(signUpRequest.getLastName());
        userDetailsEntity.setEmail(signUpRequest.getEmail());
        userDetailsEntity.setPassword(signUpRequest.getPassword());
        userDetailsEntity.setPhone(signUpRequest.getPhone());
        userDetailsEntity.setSecurityQuestion(signUpRequest.getSecurityQuestion());
        userDetailsEntity.setSecurityAnswer(signUpRequest.getSecurityAnswer());
        userDetailsEntity.setDateOfBirth(signUpRequest.getDateOfBirth());
        userDetailsEntity.setUserId(signUpRequest.getUserId());
        userDetailsRepository.save(userDetailsEntity);
    }

    private boolean isValidEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isStrongPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }

    public boolean signin(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UserDetailsEntity userDetailsEntity = userDetailsRepository.findByEmail(email);

        if (userDetailsEntity == null) {
            return false;
        }
        if (password.equals(userDetailsEntity.getPassword())) {
            System.out.println("Successful sign-in");
            return true;
        }
        return false;
    }
    public void changePassword(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String newPassword = loginRequest.getPassword();
        UserDetailsEntity userDetailsEntity = userDetailsRepository.findByEmail(email);
        if (userDetailsEntity == null) {
            throw new IllegalArgumentException("Email not found.");
        }
        userDetailsEntity.setPassword(newPassword);
        userDetailsRepository.save(userDetailsEntity);
    }

    public void deleteUser(@Valid LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String Password = loginRequest.getPassword();
        UserDetailsEntity userDetailsEntity =userDetailsRepository.findByEmail(email);
        if (userDetailsEntity == null) {
            throw new IllegalArgumentException("Email not found.");
        }
        userDetailsRepository.delete(userDetailsEntity);
    }
    }
