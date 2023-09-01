package com.challenge.ivms.userservice.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@Service
public class UserValidationService {
    private static final String USER_SERVICE_URL = "http://user-service-host:port/token/validate";

    public boolean validateToken(String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(USER_SERVICE_URL, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Token is valid, proceed with necessary actions
            // ...
            return true;
        } else {
            // Token is invalid, handle accordingly
            // ...
            return false;
        }
    }
}
