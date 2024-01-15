package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.request.StoryLineRequestDto;
import dev.task.dndquest.model.entity.adventure.StoryLine;
import org.mapstruct.Mapper;

@Mapper
public interface StoryLineMapper {
    StoryLine mapToEntity(StoryLineRequestDto dto);
}
