package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.character.PlayCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = InventoryMapper.class)
public interface PlayCharacterMapper {
    @Mapping(target = "playCharClassName", source = "playClass.name")
    @Mapping(target = "playCharRaceName", source = "race.name")
    @Mapping(target = "items", source = "entity.items")
    PlayCharacterResponseDto matToDto(PlayCharacter entity);

    PlayCharacter mapToEntity(PlayCharacterRequestDto dto);
}
