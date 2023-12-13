package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.entity.PlayCharacter;

public interface PlayCharacterService {
    PlayCharacter save(PlayCharacterRequestDto dto);
}
