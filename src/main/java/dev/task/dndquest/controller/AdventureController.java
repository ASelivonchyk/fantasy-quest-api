package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.service.StoryLineService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adventure")
@RequiredArgsConstructor
public class AdventureController {
    private final StoryLineService storyLineService;

    @GetMapping("/available")
    public List<StoryLineShortResponseDto> getAvailableAdventures(){
        return storyLineService.getAvailableStoryLines();
    }

    @GetMapping("/available/new")
    public List<StoryLineShortResponseDto> addAvailableAdventures(){
        return storyLineService.addNewStoryLinesForAvailable();
    }

    @GetMapping("/available/{serial}")
    public StoryLineFullResponseDto selectAdventure(@PathVariable Integer serial){
        return storyLineService.selectStoryline(serial);
    }
}
