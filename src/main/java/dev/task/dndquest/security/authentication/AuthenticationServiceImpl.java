package dev.task.dndquest.security.authentication;

import dev.task.dndquest.exception.BadCredentialsException;
import dev.task.dndquest.model.dto.AuthenticationResponseDto;
import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.security.jwt.JwtProvider;
import dev.task.dndquest.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PlayerService playerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthenticationResponseDto login(PlayerRequestDto dto)
            throws BadCredentialsException {
        Player player = playerService.findByLogin(dto.getLogin());
        if (!passwordEncoder.matches(dto.getPassword(), player.getPassword())) {
            throw new BadCredentialsException();
        }
        String token = jwtProvider.generateToken(dto.getLogin());
        return new AuthenticationResponseDto(token, HttpStatus.OK);
    }
}
