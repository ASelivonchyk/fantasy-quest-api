package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.request.AdventureRequestDto;
import dev.task.dndquest.model.entity.adventure.Adventure;
import dev.task.dndquest.model.entity.adventure.StoryLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = StoryLineMapper.class)
public interface AdventureMapper {
    @Mapping(target = "storyLine.id", source = "storyLineId")
    @Mapping(target = "currentStory.id", source = "currentStoryId")
    Adventure mapToEntity(AdventureRequestDto dto);

    @Mapping(target = "storyLineId", source = "id")
    @Mapping(target = "currentStoryId",
            expression = "java(storyLine.getStories().get(0).getId())")
    AdventureRequestDto mapStoryLineToAdventureDto(StoryLine storyLine);
}

