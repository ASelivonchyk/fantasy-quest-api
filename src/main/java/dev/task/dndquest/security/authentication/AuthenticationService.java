package dev.task.dndquest.security.authentication;

import dev.task.dndquest.model.dto.AuthenticationResponseDto;
import dev.task.dndquest.model.dto.PlayerRequestDto;

public interface AuthenticationService {
    AuthenticationResponseDto login(PlayerRequestDto dto);
}
