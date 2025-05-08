package com.user.curd.user_crud.service;
import com.user.curd.user_crud.dto.response.ExternalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalService.class);
    private static final String DOG_API_URL = "https://dog.ceo/api/breeds/image/random";

    private final RestTemplate restTemplate;

    public ExternalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalResponse fetchRandomDogImage() {
        ResponseEntity<ExternalResponse> response = restTemplate.getForEntity(DOG_API_URL, ExternalResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            ExternalResponse dogResponse = response.getBody();
            logger.info("Dog API Response - Message: {}, Status: {}", dogResponse.getMessage(), dogResponse.getStatus());
            return dogResponse;
        } else {
            logger.error("Failed to fetch dog image from API");
            return null;
        }
    }
}
