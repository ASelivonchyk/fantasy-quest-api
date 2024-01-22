package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.model.entity.adventure.StoryLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = StoryMapper.class)
public interface StoryLineMapper {
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "stories", source = "stories")
    StoryLine mapToEntity(StoryLineFullResponseDto dto);

    StoryLineShortResponseDto mapFullToShortDto(StoryLineFullResponseDto dto);
}
