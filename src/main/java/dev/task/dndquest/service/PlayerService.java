package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.model.dto.response.PlayerResponseDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.model.entity.character.PlayCharacter;

public interface PlayerService {
    Player save(PlayerRequestDto dto);

    Player findByLogin(String login);

    Boolean existsByLogin(String login);

    Player addCharacterToPlayer(PlayCharacter character, String playerLogin);

    PlayerResponseDto getPlayerCredentialsByLogin(String login);
}
