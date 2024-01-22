package dev.task.dndquest.parser;

import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.model.dto.response.StoryShortResponseDto;
import java.util.List;

public interface AiChatResponseParser {
    List<StoryLineShortResponseDto> parseMultipleStoryLinesFromJson(String chatResponse);

    List<StoryShortResponseDto> parseMultipleStoriesFromJson(String chatResponse);
}
