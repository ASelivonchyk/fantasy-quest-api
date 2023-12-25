package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.model.dto.response.PlayerResponseDto;
import dev.task.dndquest.model.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerDtoMapper implements DtoMapper<Player, PlayerResponseDto, PlayerRequestDto> {
    @Override
    public PlayerResponseDto matToDto(Player entity) {
        return null;
    }

    @Override
    public Player mapToEntity(PlayerRequestDto dto) {
        return new Player(null, dto.getLogin(), dto.getPassword());
    }
}
