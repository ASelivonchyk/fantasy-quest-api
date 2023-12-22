package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.ItemRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;

public interface PlayCharacterService {
    PlayCharacter save(PlayCharacterRequestDto dto);
    PlayCharacterResponseDto addItem(Long characterId, ItemRequestDto dto);
}
