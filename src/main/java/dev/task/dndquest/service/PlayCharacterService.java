package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.request.InventoryRequestDto;
import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.InventoryResponseDto;
import dev.task.dndquest.model.entity.adventure.Adventure;
import dev.task.dndquest.model.entity.character.PlayCharacter;
import java.util.List;

public interface PlayCharacterService {
    PlayCharacter save(PlayCharacterRequestDto dto);

    List<InventoryResponseDto> manageItem(Long characterId, List<InventoryRequestDto> items);

    List<InventoryResponseDto> getAllItems(Long characterId);

    void addAdventure(Adventure adventure);

    PlayCharacter findById(Long id);
}
