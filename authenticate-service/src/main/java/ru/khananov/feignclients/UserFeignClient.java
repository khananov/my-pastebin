package ru.khananov.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.khananov.dto.UserResponseDto;

@FeignClient(name = "pastebin-data-service", url = "http://cloud-gateway:8765/api/data/users")
public interface UserFeignClient {
    @GetMapping
    ResponseEntity<UserResponseDto> getByEmail(@RequestParam("email") String email);
}
