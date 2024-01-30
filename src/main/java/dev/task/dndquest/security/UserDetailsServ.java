package dev.task.dndquest.security;

import dev.task.dndquest.exception.BadCredentialsException;
import dev.task.dndquest.model.dto.response.PlayerResponseDto;
import dev.task.dndquest.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServ implements UserDetailsService {
    private final PlayerService playerService;

    @Override
    public UserDetails loadUserByUsername(String login) throws BadCredentialsException {
        PlayerResponseDto responseDto = playerService.getPlayerCredentialsByLogin(login);
        return User.withUsername(login)
                .password(responseDto.getPassword())
                .build();
    }
}
