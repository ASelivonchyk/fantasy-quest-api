package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.BadCredentialsException;
import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.model.dto.PlayerResponseDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.repository.PlayerRepository;
import dev.task.dndquest.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository repository;
    private final DtoMapper<Player,
            PlayerResponseDto, PlayerRequestDto> mapper;

    @Override
    public Player save(PlayerRequestDto dto) {
        Player player = mapper.mapToEntity(dto);
        player.setPassword(passwordEncoder.encode(dto.getPassword()));
        return repository.save(player);
    }

    @Override
    public Player findByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(BadCredentialsException::new);
    }

    @Override
    public Boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }
}
