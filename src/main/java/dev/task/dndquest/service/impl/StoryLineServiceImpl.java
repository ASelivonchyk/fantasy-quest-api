package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.StoryLineNotFoundException;
import dev.task.dndquest.mapper.StoryLineMapper;
import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.model.dto.response.StoryShortResponseDto;
import dev.task.dndquest.parser.AiChatResponseParser;
import dev.task.dndquest.service.StoryLineService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoryLineServiceImpl implements StoryLineService {
    private final ChatClient aiClient;
    private final AiChatResponseParser parser;
    private final StoryLineMapper storyLineMapper;
    private List<StoryLineShortResponseDto> availableStoryLines;

    private static final String PROMPT_STORYLINES =
            "create dnd quest in format: \"serial\", \"title\",\"description\". " +
                    "Return as json. Count of quests - 3. Serial count from:";
    private static final String PROMPT_STORY_START =
            "generate 4 parts of dnd quest with ";
    private static final String PROMPT_STORY_END =
            "in format: \"part\", \"description\", \"placeType\", \"story\". Return as json";

    @Override
    public List<StoryLineShortResponseDto> getAvailableStoryLines(){
        if (availableStoryLines == null) {
            availableStoryLines = initAvailableStoryLines();
        }
        return availableStoryLines;
    }

    @Override
    public List<StoryLineShortResponseDto> addNewStoryLinesForAvailable() {
        availableStoryLines.addAll(initAvailableStoryLines());
        return availableStoryLines;
    }

    @Override
    public StoryLineFullResponseDto selectStoryline(Integer serialNumber) {
        StoryLineShortResponseDto storyLine;
        try {
            storyLine = availableStoryLines.get(serialNumber - 1);
        } catch (IndexOutOfBoundsException e){
            throw new StoryLineNotFoundException();
        }
        StoryLineFullResponseDto fullStoryLineDto = storyLineMapper.mapShortToFullDto(storyLine);
        fullStoryLineDto.setStories(initStories(createFullStoryPrompt(storyLine)));
        return fullStoryLineDto;
    }

    private List<StoryLineShortResponseDto> initAvailableStoryLines() {
        String storyLineSerialNumber = String.format("%d",
                availableStoryLines == null ? 1 : availableStoryLines.size() + 1);
        return parser.parseMultipleStoryLinesFromJSON(
                        aiClient.generate(PROMPT_STORYLINES + storyLineSerialNumber));
    }

    private List<StoryShortResponseDto> initStories(String storyPrompt) {
        return parser.parseMultipleStoriesFromJSON(aiClient.generate(storyPrompt));
    }

    private String createFullStoryPrompt(StoryLineShortResponseDto storyLine){
        String promptMiddle = String.format("tile: %s and description: %s.",
                storyLine.getTitle(), storyLine.getDescription());
        return String.format("%s %s %s",PROMPT_STORY_START, promptMiddle, PROMPT_STORY_END);
    }
}
