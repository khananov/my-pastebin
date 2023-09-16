package ru.khananov.security.jwt;

import ru.khananov.dto.UserResponseDto;

import java.util.ArrayList;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(UserResponseDto user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}