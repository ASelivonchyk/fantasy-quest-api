package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import java.util.List;

public interface StoryLineService {
    List<StoryLineShortResponseDto> getAvailableStoryLines();
    List<StoryLineShortResponseDto> addNewStoryLinesForAvailable();
    StoryLineFullResponseDto selectStoryline(Integer serialNumber);
}
