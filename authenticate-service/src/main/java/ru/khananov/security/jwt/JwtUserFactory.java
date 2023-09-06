package ru.khananov.security.jwt;

import ru.khananov.entities.PasteUser;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(PasteUser pasteUser) {
        return new JwtUser(
                pasteUser.getId(),
                pasteUser.getEmail(),
                pasteUser.getPassword(),
                null
        );
    }
}
