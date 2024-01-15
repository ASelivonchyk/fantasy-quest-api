package dev.task.dndquest.parser;

import dev.task.dndquest.model.dto.request.StoryLineRequestDto;
import dev.task.dndquest.model.entity.adventure.StoryLine;

import java.util.List;

public interface AiChatResponseParser {
    List<StoryLineRequestDto> parseMultipleStoriesFromJSON(String chatResponse);
    StoryLine parseStorylineFromJSON(String chatResponse);
}
