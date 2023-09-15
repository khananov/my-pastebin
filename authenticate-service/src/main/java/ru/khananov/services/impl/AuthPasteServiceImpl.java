package ru.khananov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khananov.data.entities.dto.PasteRequestDto;
import ru.khananov.data.entities.dto.PasteResponseDto;
import ru.khananov.feignclients.PasteFeignClient;
import ru.khananov.security.jwt.JwtTokenProvider;
import ru.khananov.services.AuthPasteService;

@Service
public class AuthPasteServiceImpl implements AuthPasteService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasteFeignClient pasteFeignClient;

    @Autowired
    public AuthPasteServiceImpl(JwtTokenProvider jwtTokenProvider, PasteFeignClient pasteFeignClient) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.pasteFeignClient = pasteFeignClient;
    }

    @Override
    public PasteResponseDto getPaste(String hash, String authHeader) {
        String email = getEmailFromAuthHeader(authHeader);
        return pasteFeignClient.getByHash(hash, email).getBody();
    }

    @Override
    public PasteResponseDto savePaste(PasteRequestDto pasteRequestDto, String authHeader) {
        String email = getEmailFromAuthHeader(authHeader);
        return pasteFeignClient.save(pasteRequestDto, email).getBody();
    }

    private String getEmailFromAuthHeader(String authHeader) {
        String token = jwtTokenProvider.extractToken(authHeader);
        return jwtTokenProvider.getUserEmail(token);
    }
}
