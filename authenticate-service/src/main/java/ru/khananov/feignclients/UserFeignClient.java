package ru.khananov.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.khananov.entities.User;

@FeignClient(name = "pastebin-data-service", url = "http://localhost:8765/api/data/users")
public interface UserFeignClient {
    @GetMapping
    ResponseEntity<User> getByEmail(@RequestParam("email") String email);

    @PostMapping
    ResponseEntity<?> save(@RequestBody User user);
}
