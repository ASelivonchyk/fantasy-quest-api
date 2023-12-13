package dev.task.dndquest.service.impl;

import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.repository.PlayCharacterRepository;
import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.service.PlayCharacterService;
import dev.task.dndquest.service.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayCharacterServiceImpl implements PlayCharacterService {
    private final PlayCharacterRepository repository;
    private final PlayCharacterClassService pccService;
    private final RaceService raceService;
    private final DtoMapper<PlayCharacter,
            PlayCharacterResponseDto, PlayCharacterRequestDto> mapper;

    @Override
    public PlayCharacter save(PlayCharacterRequestDto dto) {
        PlayCharacter playCharacter = mapper.mapToEntity(dto);
        playCharacter.setClas(pccService.findByName(dto.getPlayCharClass()));
        playCharacter.setRace(raceService.findByName(dto.getPlayCharRace()));
        return repository.save(playCharacter);
    }
}
