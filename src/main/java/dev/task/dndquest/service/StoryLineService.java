package dev.task.dndquest.service;

import dev.task.dndquest.model.entity.adventure.StoryLine;

import java.util.List;

public interface StoryLineService {
    List<StoryLine> saveAll(String aiChatResponse);
}
