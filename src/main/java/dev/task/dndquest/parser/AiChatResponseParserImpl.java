package dev.task.dndquest.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.task.dndquest.model.dto.request.StoryLineRequestDto;
import dev.task.dndquest.model.entity.adventure.StoryLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AiChatResponseParserImpl implements AiChatResponseParser{
    private final ObjectMapper mapper;

    @Override
    public List<StoryLineRequestDto> parseMultipleStoriesFromJSON(String chatResponse) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(chatResponse, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("error while parsing json");
        }
    }

    @Override
    public StoryLine parseStorylineFromJSON(String chatResponse) {
        return null;
    }
}
