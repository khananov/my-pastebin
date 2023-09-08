package ru.khananov.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RestTemplate restTemplate;

    @Autowired
    public RegistrationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendUserRequest(UserRegistrationRequestDto userRegistrationRequestDto) {
        String url = "http://localhost:8765/api/registration";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser;

        try {
            jsonUser = objectMapper.writeValueAsString(userRegistrationRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> entity = new HttpEntity<>(jsonUser, headers);

        restTemplate.postForObject(url, entity, String.class);
    }
}
