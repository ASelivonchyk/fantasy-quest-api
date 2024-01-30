package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.service.StoryLineService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adventure")
@RequiredArgsConstructor
public class AdventureController {
    private final StoryLineService storyLineService;

    @GetMapping("/available")
    public List<StoryLineShortResponseDto> getAvailableAdventures(Authentication auth) {
        return storyLineService.getAvailableStoryLines(auth);
    }

    @GetMapping("/available/new")
    public List<StoryLineShortResponseDto> addAvailableAdventures(Authentication auth) {
        return storyLineService.addNewStoryLinesForAvailable(auth);
    }

    @GetMapping("/available/{serial}")
    public StoryLineFullResponseDto selectAdventure(
            @PathVariable Integer serial, Authentication auth) {
        return storyLineService.selectStoryLine(serial, auth);
    }

    @PostMapping("/available/{serial}")
    public String startAdventure(
            @PathVariable Integer serial, Authentication auth) {
        storyLineService.startStoryLine(serial, auth);
        /* TODO:
           create adventure endpoint to manage players adventures
           and implement redirection to it from here
        */
        return "forward:/";
    }
}
