package ru.khananov.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.khananov.dto.UserResponseDto;
import ru.khananov.feignclients.UserFeignClient;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserFeignClient userFeignClient;

    @Autowired
    public JwtUserDetailsService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserResponseDto user = userFeignClient.getByEmail(email).getBody();

        return JwtUserFactory.create(user);
    }
}