package com.user.curd.user_crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserCrudApplication {

	private static final Logger logger = LoggerFactory.getLogger(UserCrudApplication.class);

	public static void main(String[] args) {
		logger.info("Starting User CRUD Application...");
		SpringApplication.run(UserCrudApplication.class, args);
		logger.info("User CRUD Application started successfully.");
	}
}
