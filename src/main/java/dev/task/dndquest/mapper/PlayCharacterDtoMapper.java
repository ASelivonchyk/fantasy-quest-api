package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.ItemResponseDto;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.model.entity.PlayCharacterClass;
import dev.task.dndquest.model.entity.Race;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PlayCharacterDtoMapper
        implements DtoMapper<PlayCharacter, PlayCharacterResponseDto, PlayCharacterRequestDto> {
    @Override
    public PlayCharacterResponseDto matToDto(PlayCharacter entity) {

        return new PlayCharacterResponseDto(entity.getName(),
                entity.getPlayClass().getName(),
                entity.getRace().getName(),
                entity.getStrength(),
                entity.getDexterity(),
                entity.getConstitution(),
                entity.getIntelligence(),
                entity.getWisdom(),
                entity.getCharisma(),
                entity.getItems().entrySet().stream()
                        .map(e -> new ItemResponseDto(e.getKey().getName(), e.getValue()))
                        .collect(Collectors.toList()));
    }

    @Override
    public PlayCharacter mapToEntity(PlayCharacterRequestDto dto) {
        PlayCharacterClass playCharacterClass = new PlayCharacterClass();
        playCharacterClass.setName(dto.getPlayCharClass());
        Race race = new Race();
        race.setName(dto.getPlayCharRace());
        return new PlayCharacter(
                dto.getName(),
                playCharacterClass,
                race,
                dto.getStrength(),
                dto.getDexterity(),
                dto.getConstitution(),
                dto.getIntelligence(),
                dto.getWisdom(),
                dto.getCharisma());
    }
}
