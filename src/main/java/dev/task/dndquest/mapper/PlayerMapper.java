package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.model.entity.Player;
import org.mapstruct.Mapper;

@Mapper
public interface PlayerMapper {
    Player mapToEntity(PlayerRequestDto dto);
}
