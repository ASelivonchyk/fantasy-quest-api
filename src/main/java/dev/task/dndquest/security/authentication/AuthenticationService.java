package dev.task.dndquest.security.authentication;

import dev.task.dndquest.model.dto.response.AuthenticationResponseDto;
import dev.task.dndquest.model.dto.request.PlayerRequestDto;

public interface AuthenticationService {
    AuthenticationResponseDto login(PlayerRequestDto dto);
}
