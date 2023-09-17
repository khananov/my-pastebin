package ru.khananov.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khananov.dto.UserRegistrationRequestDto;
import ru.khananov.clients.UserFeignClient;
import ru.khananov.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private final UserFeignClient userFeignClient;

    @Autowired
    public RegistrationServiceImpl(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public void registration(UserRegistrationRequestDto user) {
        log.info("save user, email - " + user.getEmail());
        userFeignClient.save(user);
    }
}