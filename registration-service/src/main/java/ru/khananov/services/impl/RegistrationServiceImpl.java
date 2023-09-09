package ru.khananov.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.exceptions.PasswordDoesntMatchException;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.feignclients.UserFeignClient;
import ru.khananov.mappers.UserMapper;
import ru.khananov.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserFeignClient userFeignClient;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserFeignClient userFeignClient,
                                   UserMapper userMapper,
                                   PasswordEncoder passwordEncoder) {
        this.userFeignClient = userFeignClient;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registration(UserRegistrationRequestDto userRegistrationRequestDto) {
        encodePassword(userRegistrationRequestDto);

        User user = userMapper.toEntity(userRegistrationRequestDto);

        if (userFeignClient.getByEmail(user.getEmail()).getBody() != null) {
            log.error(new UserAlreadyExistException(user.getEmail()).getMessage());
            throw new UserAlreadyExistException(user.getEmail());
        }

        log.info("save user, email - " + user.getEmail());
        userFeignClient.save(user);
    }

    private void encodePassword(UserRegistrationRequestDto user) {
        if (checkPasswordMatching(user)) {
            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );

            user.setRepeatPassword(
                    passwordEncoder.encode(user.getRepeatPassword())
            );
        }
    }

    private boolean checkPasswordMatching(UserRegistrationRequestDto user) {
        if (!user.getPassword().equals(user.getRepeatPassword()))
            throw new PasswordDoesntMatchException("Password doesn't match");

        log.info("Passwords match");
        return true;
    }
}