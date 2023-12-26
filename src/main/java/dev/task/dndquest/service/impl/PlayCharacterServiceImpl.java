package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.CharNotFoundException;
import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.ItemOperations;
import dev.task.dndquest.model.dto.request.InventoryRequestDto;
import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.InventoryResponseDto;
import dev.task.dndquest.model.dto.response.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.Item;
import dev.task.dndquest.model.entity.PlayCharacter;
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
    private final DtoMapper<PlayCharacter,
            PlayCharacterResponseDto, PlayCharacterRequestDto> mapper;

    @Override
    public PlayCharacter save(PlayCharacterRequestDto dto) {
        PlayCharacter playCharacter = mapper.mapToEntity(dto);
        playCharacter.setPlayClass(classService.findByName(dto.getPlayCharClass()));
        playCharacter.setRace(raceService.findByName(dto.getPlayCharRace()));
        return repository.save(playCharacter);
    }

    @Override
    public List<InventoryResponseDto> manageItem(Long characterId, InventoryRequestDto dto) {
        PlayCharacter character = repository.findById(characterId)
                                            .orElseThrow(CharNotFoundException::new);
        applyOperationWithItems(character, dto);
        return mapper.matToDto(repository.save(character)).getItems();
    }

    @Override
    public List<InventoryResponseDto> getAllItems(Long characterId) {
        PlayCharacter character = repository.findById(characterId).orElseThrow(CharNotFoundException::new);
        return mapper.matToDto(character).getItems();
    }

    private void applyOperationWithItems(PlayCharacter character, InventoryRequestDto dto){
        Item item = itemService.findByName(dto.getItem());
        Map<Item, Integer> itemsInInventory = character.getItems();
        Integer itemsCount = itemsInInventory.get(item) == null ? 0 : itemsInInventory.get(item);
        Integer totalItemsCount = ItemOperations.getMap()
                .get(dto.getOperation())
                .applyOperation(itemsCount, dto.getCount());
        itemsInInventory.put(item, totalItemsCount);
    }
}
