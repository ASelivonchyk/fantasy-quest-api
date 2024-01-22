package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.response.StoryShortResponseDto;
import dev.task.dndquest.model.entity.adventure.Story;
import org.mapstruct.Mapper;

@Mapper
public interface StoryMapper {
    default Story mapToEntity(StoryShortResponseDto dto) {
        return new Story(null,
                Integer.parseInt(dto.getPart().replaceAll("[^0-9]", "")),
                dto.getDescription(),
                dto.getPlaceType(),
                dto.getStory(),
                null);
    }
}
