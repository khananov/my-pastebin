package ru.khananov.data.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.khananov.data.entities.User;
import ru.khananov.data.entities.dto.UserAuthRequestDto;
import ru.khananov.data.entities.dto.UserAuthResponseDto;
import ru.khananov.feignclients.UserFeignClient;
import ru.khananov.security.jwt.JwtTokenProvider;
import ru.khananov.data.services.AuthenticateService;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFeignClient userFeignClient;

    @Autowired
    public AuthenticateServiceImpl(AuthenticationManager authenticationManager,
                                   JwtTokenProvider jwtTokenProvider,
                                   UserFeignClient userFeignClient) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserAuthResponseDto authenticate(UserAuthRequestDto userAuthRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthRequestDto.getEmail(),
                        userAuthRequestDto.getPassword()
                )
        );

        User user = userFeignClient.getByEmail(userAuthRequestDto.getEmail()).getBody();
        String token = "";

        if (user != null) {
            token = jwtTokenProvider.createToken(user.getEmail());
        }

        return new UserAuthResponseDto(token);
    }
}