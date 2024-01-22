package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface StoryLineService {
    List<StoryLineShortResponseDto> getAvailableStoryLines(Authentication authentication);

    List<StoryLineShortResponseDto> addNewStoryLinesForAvailable(Authentication authentication);

    StoryLineFullResponseDto selectStoryline(Integer serialNumber, Authentication authentication);
}
