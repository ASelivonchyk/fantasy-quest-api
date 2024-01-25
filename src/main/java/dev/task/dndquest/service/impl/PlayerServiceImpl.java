package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.BadCredentialsException;
import dev.task.dndquest.exception.DuplicateLoginException;
import dev.task.dndquest.mapper.PlayerMapper;
import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.model.dto.response.PlayerResponseDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.model.entity.character.PlayCharacter;
import dev.task.dndquest.repository.PlayerRepository;
import dev.task.dndquest.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository repository;
    private final PlayerMapper mapper;

    @Override
    public Player save(PlayerRequestDto dto) {
        if (repository.existsByLogin(dto.getLogin())) {
            throw new DuplicateLoginException();
        }
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

    @Override
    public Player addCharacterToPlayer(PlayCharacter character, String playerLogin){
        Player player = findByLogin(playerLogin);
        player.setCharacter(character);
        return repository.save(player);
    }

    @Override
    @Cacheable(value = "player", key = "#login")
    public PlayerResponseDto getPlayerCredentialsByLogin(String login) {
        return  mapper.mapToDto(
                repository.findByLogin(login).orElseThrow(BadCredentialsException::new));
    }
}
