package dev.task.dndquest.security.authentication;

import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.model.dto.response.AuthenticationResponseDto;

public interface AuthenticationService {
    AuthenticationResponseDto login(PlayerRequestDto dto);
}
