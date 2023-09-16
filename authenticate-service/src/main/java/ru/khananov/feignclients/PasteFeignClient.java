package ru.khananov.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.dto.PasteRequestDto;
import ru.khananov.dto.PasteResponseDto;

@FeignClient(name = "paste-data-service", url = "http://cloud-gateway:8765/api/data/pastes")
public interface PasteFeignClient {

    @GetMapping("/{hash}")
    ResponseEntity<PasteResponseDto> getByHash(@PathVariable String hash, @RequestParam String email);

    @PostMapping
    ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto, @RequestParam String email);
}