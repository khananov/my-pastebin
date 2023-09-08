package ru.khananov.security.jwt;

import ru.khananov.entities.User;

import java.util.ArrayList;
import java.util.Arrays;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                Arrays.toString(user.getPassword()),
                new ArrayList<>()
        );
    }
}
