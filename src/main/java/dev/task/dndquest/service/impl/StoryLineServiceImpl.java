package dev.task.dndquest.service.impl;

import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.request.StoryLineRequestDto;
import dev.task.dndquest.model.dto.response.StoryLineResponseDto;
import dev.task.dndquest.model.entity.adventure.StoryLine;
import dev.task.dndquest.parser.AiChatResponseParser;
import dev.task.dndquest.repository.StoryLineRepository;
import dev.task.dndquest.service.StoryLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryLineServiceImpl implements StoryLineService {
    private final ChatClient aiClient;
    private final StoryLineRepository repository;
    private final AiChatResponseParser parser;
    private final DtoMapper<StoryLine,
            StoryLineResponseDto, StoryLineRequestDto> mapper;

    @Override
    public List<StoryLine> saveAll(String prompt) {
        List<StoryLine> stories = parser.parseMultipleStoriesFromJSON(
                aiClient.generate(prompt)).stream()
                                          .map(mapper::mapToEntity)
                                          .toList();
        return repository.saveAll(stories);
    }
}
