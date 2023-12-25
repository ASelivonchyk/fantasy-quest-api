package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.model.entity.Player;

public interface PlayerService {
    Player save(PlayerRequestDto dto);
    Player findByLogin(String login);
    Boolean existsByLogin(String login);
}
