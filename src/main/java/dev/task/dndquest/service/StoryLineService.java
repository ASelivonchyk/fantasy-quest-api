package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface StoryLineService {
    List<StoryLineShortResponseDto> getAvailableStoryLines(Authentication authentication);
    List<StoryLineShortResponseDto> addNewStoryLinesForAvailable(Authentication authentication);
    StoryLineFullResponseDto selectStoryline(Integer serialNumber, Authentication authentication);
}
