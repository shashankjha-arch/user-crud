package com.user.curd.user_crud.service;

import com.user.curd.user_crud.data.entity.UserDetailsEntity;
import com.user.curd.user_crud.data.repository.UserDetailsRepository;
import com.user.curd.user_crud.dto.request.LoginRequest;
import com.user.curd.user_crud.dto.request.SignUpRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public void signUp(@Valid SignUpRequest signUpRequest) {
        logger.info("Starting user registration for email: {}", signUpRequest.getEmail());

        if (!isValidEmailFormat(signUpRequest.getEmail())) {
            logger.warn("Invalid email format attempted: {}", signUpRequest.getEmail());
            throw new IllegalArgumentException("Invalid email format.");
        }

        if (userDetailsRepository.findByEmail(signUpRequest.getEmail()) != null) {
            logger.warn("Attempt to register with existing email: {}", signUpRequest.getEmail());
            throw new IllegalArgumentException("Email is already registered.");
        }

        if (!isStrongPassword(signUpRequest.getPassword())) {
            logger.warn("Weak password attempt for email: {}", signUpRequest.getEmail());
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain a mix of letters, numbers, and special characters.");
        }

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
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
        logger.info("User registered successfully with email: {}", signUpRequest.getEmail());
    }

    public boolean signin(LoginRequest loginRequest) {
        logger.info("User attempting to sign in with email: {}", loginRequest.getEmail());
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserDetailsEntity userDetailsEntity = userDetailsRepository.findByEmail(email);

        if (userDetailsEntity == null) {
            logger.warn("Sign-in failed: No user found with email: {}", email);
            return false;
        }

        if (password.equals(userDetailsEntity.getPassword())) {
            logger.info("User signed in successfully: {}", email);
            return true;
        } else {
            logger.warn("Sign-in failed: Incorrect password for email: {}", email);
            return false;
        }
    }

    public void changePassword(LoginRequest loginRequest) {
        logger.info("Attempting password change for email: {}", loginRequest.getEmail());

        String email = loginRequest.getEmail();
        String newPassword = loginRequest.getPassword();

        UserDetailsEntity userDetailsEntity = userDetailsRepository.findByEmail(email);

        if (userDetailsEntity == null) {
            logger.warn("Password change failed: Email not found: {}", email);
            throw new IllegalArgumentException("Email not found.");
        }

        userDetailsEntity.setPassword(newPassword);
        userDetailsRepository.save(userDetailsEntity);
        logger.info("Password changed successfully for email: {}", email);
    }

    public void deleteUser(@Valid LoginRequest loginRequest) {
        logger.info("Attempting to delete user with email: {}", loginRequest.getEmail());

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserDetailsEntity userDetailsEntity = userDetailsRepository.findByEmail(email);

        if (userDetailsEntity == null) {
            logger.warn("User deletion failed: Email not found: {}", email);
            throw new IllegalArgumentException("Email not found.");
        }

        userDetailsRepository.delete(userDetailsEntity);
        logger.info("User deleted successfully: {}", email);
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
}
