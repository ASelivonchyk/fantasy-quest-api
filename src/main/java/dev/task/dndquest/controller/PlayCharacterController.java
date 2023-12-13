package dev.task.dndquest.controller;

import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.service.PlayCharacterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/character")
@AllArgsConstructor
public class PlayCharacterController {
    PlayCharacterService playCharacterService;
    DtoMapper<PlayCharacter, PlayCharacterResponseDto, PlayCharacterRequestDto> mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCharacter(@Valid @RequestBody
                                PlayCharacterRequestDto playCharacterDto){
        playCharacterService.save(playCharacterDto);
    }
}
