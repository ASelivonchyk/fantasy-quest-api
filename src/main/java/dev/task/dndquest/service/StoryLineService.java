package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface StoryLineService {
    List<StoryLineShortResponseDto> getAvailableStoryLines(Authentication authentication);

    List<StoryLineShortResponseDto> addNewStoryLinesForAvailable(Authentication authentication);

    StoryLineFullResponseDto selectStoryLine(Integer serialNumber, Authentication authentication);

    String startStoryLine(Integer serialNumber, Authentication authentication);
}
