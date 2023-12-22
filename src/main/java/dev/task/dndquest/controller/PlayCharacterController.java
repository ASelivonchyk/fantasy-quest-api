package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.ItemRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.service.PlayCharacterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
public class PlayCharacterController {
    private final PlayCharacterService playCharacterService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCharacter(@Valid @RequestBody
                                PlayCharacterRequestDto playCharacterDto){
        playCharacterService.save(playCharacterDto);
    }

    @PutMapping
    public PlayCharacterResponseDto addItem(@RequestParam Long id,
                                            @RequestBody ItemRequestDto dto) {
        return playCharacterService.addItem(id, dto);
    }
}
