package dev.task.dndquest.parser;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryShortResponseDto;
import java.util.List;

public interface AiChatResponseParser {
    List<StoryLineFullResponseDto> parseMultipleStoryLinesFromJson(String chatResponse);

    List<StoryShortResponseDto> parseMultipleStoriesFromJson(String chatResponse);
}
