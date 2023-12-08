package dev.task.dndquest.controller;

import dev.task.dndquest.exception.DBException;
import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.service.PlayCharacterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/character")
@AllArgsConstructor
public class PlayCharacterController {
    private PlayCharacterService playCharacterService;
    private DtoMapper<PlayCharacter, PlayCharacterResponseDto, PlayCharacterRequestDto> mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCharacter(@Valid @RequestBody PlayCharacterRequestDto playCharacterDto){
        PlayCharacter playCharacter = mapper.mapToEntity(playCharacterDto);
        try {
           playCharacterService.save(playCharacter);
        } catch (DBException e) {
            throw new ResponseStatusException(
                    HttpStatus.PRECONDITION_FAILED, e.getMessage(), e);
        }

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(ex.getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(System.lineSeparator())));
    }
}
