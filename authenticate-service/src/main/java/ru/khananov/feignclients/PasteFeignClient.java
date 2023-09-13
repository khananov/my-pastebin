package ru.khananov.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.data.entities.dto.PasteRequestDto;
import ru.khananov.data.entities.dto.PasteResponseDto;

@FeignClient(name = "paste-data-service", url = "http://localhost:8765/api/data/pastes")
public interface PasteFeignClient {

    @GetMapping("/{hash}")
    ResponseEntity<PasteResponseDto> getByHash(@PathVariable String hash, @RequestParam String email);

    @PostMapping
    ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto, @RequestParam String email);
}