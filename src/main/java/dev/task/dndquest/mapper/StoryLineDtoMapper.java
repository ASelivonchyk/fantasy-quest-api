package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.request.StoryLineRequestDto;
import dev.task.dndquest.model.dto.response.StoryLineResponseDto;
import dev.task.dndquest.model.entity.adventure.StoryLine;
import org.springframework.stereotype.Component;

@Component
public class StoryLineDtoMapper implements DtoMapper<StoryLine, StoryLineResponseDto, StoryLineRequestDto>  {
    @Override
    public StoryLineResponseDto matToDto(StoryLine entity) {
        return null;
    }

    @Override
    public StoryLine mapToEntity(StoryLineRequestDto dto) {
        return new StoryLine(dto.getTitle(), dto.getDescription());
    }
}
