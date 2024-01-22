package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.CharNotFoundException;
import dev.task.dndquest.mapper.PlayCharacterMapper;
import dev.task.dndquest.model.ItemOperations;
import dev.task.dndquest.model.dto.request.InventoryRequestDto;
import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.InventoryResponseDto;
import dev.task.dndquest.model.entity.character.PlayCharacter;
import dev.task.dndquest.model.entity.item.Item;
import dev.task.dndquest.repository.PlayCharacterRepository;
import dev.task.dndquest.service.ItemService;
import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.service.PlayCharacterService;
import dev.task.dndquest.service.RaceService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayCharacterServiceImpl implements PlayCharacterService {
    private final PlayCharacterRepository repository;
    private final PlayCharacterClassService classService;
    private final RaceService raceService;
    private final ItemService itemService;
    private final PlayCharacterMapper mapper;

    @Override
    public PlayCharacter save(PlayCharacterRequestDto dto) {
        PlayCharacter playCharacter = mapper.mapToEntity(dto);
        playCharacter.setPlayClass(classService.findByName(dto.getPlayCharClass()));
        playCharacter.setRace(raceService.findByName(dto.getPlayCharRace()));
        return repository.save(playCharacter);
    }

    @Override
    public List<InventoryResponseDto> manageItem(
            Long characterId, List<InventoryRequestDto> items) {
        PlayCharacter character = repository.findById(characterId)
                                            .orElseThrow(CharNotFoundException::new);
        applyOperationWithItems(character, items);
        return mapper.matToDto(repository.save(character)).getItems();
    }

    @Override
    public List<InventoryResponseDto> getAllItems(Long characterId) {
        PlayCharacter character = repository.findById(characterId)
                .orElseThrow(CharNotFoundException::new);
        return mapper.matToDto(character).getItems();
    }

    private void applyOperationWithItems(
            PlayCharacter character, List<InventoryRequestDto> items) {
        items.forEach(inventoryDto -> {
            Item item = itemService.findByName(inventoryDto.getItem());
            Map<Item, Integer> itemsInInventory = character.getItems();
            Integer totalItemsCount = ItemOperations.getMap()
                    .get(inventoryDto.getOperation())
                    .applyOperation(itemsInInventory.getOrDefault(item, 0),
                            inventoryDto.getCount());
            itemsInInventory.put(item, totalItemsCount);
        });
    }
}
