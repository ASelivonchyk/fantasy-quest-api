package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface StoryLineMapper {
    StoryLineFullResponseDto mapShortToFullDto (StoryLineShortResponseDto dto);
}
