package ru.khananov.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;
import ru.khananov.entities.dto.PasteUserRequestDto;
import ru.khananov.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RestTemplate restTemplate;

    @Autowired
    public RegistrationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendUserRequest(PasteUserRequestDto pasteUserRequestDto) {
        String url = "http://localhost:8765/api/registration";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser;

        try {
            jsonUser = objectMapper.writeValueAsString(pasteUserRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> entity = new HttpEntity<>(jsonUser, headers);

        restTemplate.postForObject(url, entity, String.class);
    }
}
