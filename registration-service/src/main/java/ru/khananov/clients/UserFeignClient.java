package ru.khananov.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.khananov.dto.UserRegistrationRequestDto;
import ru.khananov.dto.UserResponseDto;

@FeignClient(name = "pastebin-data-service", url = "http://cloud-gateway:8765/api/data/users")
public interface UserFeignClient {
    @PostMapping
    ResponseEntity<UserResponseDto> save(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto);
}