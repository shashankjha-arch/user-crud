package com.user.curd.user_crud.controller;

import com.user.curd.user_crud.dto.response.ExternalResponse;
import com.user.curd.user_crud.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalController {

    @Autowired
    private ExternalService dogService;

    @GetMapping("/random-dog")
    public ExternalResponse getRandomDogImage() {
        return dogService.fetchRandomDogImage();
    }
}


