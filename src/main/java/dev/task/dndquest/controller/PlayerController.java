package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.security.authentication.AuthenticationService;
import dev.task.dndquest.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@Valid @RequestBody PlayerRequestDto playerDto) {
        playerService.save(playerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody  PlayerRequestDto playerDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.login(playerDto));
    }
}
