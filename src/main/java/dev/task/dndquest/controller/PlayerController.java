package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.security.authentication.AuthenticationService;
import dev.task.dndquest.security.jwt.JwtProvider;
import dev.task.dndquest.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
@AllArgsConstructor
public class PlayerController {
    PlayerService playerService;
    AuthenticationService authenticationService;
    JwtProvider jwtProvider;

    @GetMapping
    public String hello(Authentication authentication, HttpServletRequest request){
        return "hello world!";
    }

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
