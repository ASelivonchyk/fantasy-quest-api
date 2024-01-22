package dev.task.dndquest.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.model.dto.response.StoryShortResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiChatResponseParserImpl implements AiChatResponseParser {
    private final ObjectMapper mapper;

    @Override
    public List<StoryLineShortResponseDto> parseMultipleStoryLinesFromJson(String chatResponse) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(
                    retrieveAiResponse(chatResponse),
                    new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("error while parsing json");
        }
    }

    @Override
    public List<StoryShortResponseDto> parseMultipleStoriesFromJson(String chatResponse) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(
                    retrieveAiResponse(chatResponse),
                    new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("error while parsing json");
        }
    }

    private String retrieveAiResponse(String aiRequest) {
        if (aiRequest.indexOf("[") != 0) {
            return aiRequest.substring(
                    aiRequest.indexOf("["),
                    aiRequest.indexOf("]") + 1);
        }
        return aiRequest;
    }
}
