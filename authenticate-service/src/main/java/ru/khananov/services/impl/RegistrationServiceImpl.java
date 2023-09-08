package ru.khananov.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.khananov.entities.dto.UserAuthResponseDto;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.security.jwt.JwtTokenProvider;
import ru.khananov.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RestTemplate restTemplate;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public RegistrationServiceImpl(RestTemplate restTemplate,
                                   JwtTokenProvider jwtTokenProvider) {
        this.restTemplate = restTemplate;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserAuthResponseDto sendUserRequest(UserRegistrationRequestDto userRegistrationRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonUser = mapUserToJson(userRegistrationRequestDto);
        String token = jwtTokenProvider.createToken(userRegistrationRequestDto.getEmail());

        HttpEntity<String> entity = new HttpEntity<>(jsonUser, headers);

        String url_registration = "http://localhost:8765/api/registration";
        restTemplate.postForObject(url_registration, entity, String.class);

        return new UserAuthResponseDto(token);
    }

    private String mapUserToJson(UserRegistrationRequestDto user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}