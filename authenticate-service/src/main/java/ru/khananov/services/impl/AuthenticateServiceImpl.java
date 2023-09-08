package ru.khananov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserAuthRequestDto;
import ru.khananov.entities.dto.UserAuthResponseDto;
import ru.khananov.security.jwt.JwtTokenProvider;
import ru.khananov.services.AuthenticateService;
import ru.khananov.services.UserService;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticateServiceImpl(AuthenticationManager authenticationManager,
                                   JwtTokenProvider jwtTokenProvider,
                                   UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public UserAuthResponseDto authenticate(UserAuthRequestDto userAuthRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthRequestDto.getEmail(),
                        userAuthRequestDto.getPassword()
                )
        );

        User user = userService.getByEmail(userAuthRequestDto.getEmail());
        String token = jwtTokenProvider.createToken(user.getEmail());

        return new UserAuthResponseDto(token);
    }
}