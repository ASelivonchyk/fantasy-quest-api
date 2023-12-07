package dev.task.dndquest.controller;

import dev.task.dndquest.exception.DBException;
import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.service.PlayCharacterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/character")
@AllArgsConstructor
public class PlayCharacterController {
    PlayCharacterService playCharacterService;
    DtoMapper<PlayCharacter, PlayCharacterResponseDto, PlayCharacterRequestDto> mapper;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void createCharacter(@Valid @RequestBody PlayCharacterRequestDto playCharacterDto){
        try {
            PlayCharacter playCharacter = mapper.mapToEntity(playCharacterDto);
            playCharacterService.save(playCharacter);
        } catch (DBException e) {
            throw new ResponseStatusException(
                    HttpStatus.PRECONDITION_FAILED, e.getMessage(), e);
        }
    }
}
