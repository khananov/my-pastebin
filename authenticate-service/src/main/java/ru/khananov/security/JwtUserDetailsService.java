package ru.khananov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.khananov.entities.PasteUser;
import ru.khananov.services.PasteUserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final PasteUserService pasteUserService;

    @Autowired
    public JwtUserDetailsService(PasteUserService pasteUserService) {
        this.pasteUserService = pasteUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PasteUser pasteUser = pasteUserService.getByEmail(email);

    }
}
