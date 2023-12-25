package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.CharNotFoundException;
import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.ItemRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.Item;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.repository.PlayCharacterRepository;
import dev.task.dndquest.service.ItemService;
import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.service.PlayCharacterService;
import dev.task.dndquest.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public PlayCharacterResponseDto addItem(Long characterId, ItemRequestDto dto) {
        PlayCharacter character = repository.findById(characterId)
                                            .orElseThrow(CharNotFoundException::new);
        addItemsToInventory(character, dto);
        return mapper.matToDto(repository.save(character));
    }

    private void addItemsToInventory(PlayCharacter character, ItemRequestDto dto){
        Item item = itemService.findByName(dto.getName());
        Map<Item, Integer> items = character.getItems();
        int itemCount = items.get(item) == null ? 0 : items.get(item);
        items.put(item, dto.getCount() + itemCount);
    }
}
