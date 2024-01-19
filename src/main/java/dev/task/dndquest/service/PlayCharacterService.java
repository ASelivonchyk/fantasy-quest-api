package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.request.InventoryRequestDto;
import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.InventoryResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;
import java.util.List;

public interface PlayCharacterService {
    PlayCharacter save(PlayCharacterRequestDto dto);
    List<InventoryResponseDto> manageItem(Long characterId, InventoryRequestDto dto);
    List<InventoryResponseDto> getAllItems(Long characterId);
}
