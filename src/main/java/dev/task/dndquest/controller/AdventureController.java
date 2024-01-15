package dev.task.dndquest.controller;

import dev.task.dndquest.model.entity.adventure.StoryLine;
import dev.task.dndquest.service.StoryLineService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adventure")
@RequiredArgsConstructor
public class AdventureController {
    private final StoryLineService storyLineService;

    @GetMapping("/all")
    public String getAvailableAdventures(){
        String prompt = "generate 3 different dnd quests in format \"title\",\"description\". Return as json";
        List<StoryLine> storyLines = storyLineService.saveAll(prompt);
        return "done";
    }
}
