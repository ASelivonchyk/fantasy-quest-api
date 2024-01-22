package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.request.InventoryRequestDto;
import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.InventoryResponseDto;
import dev.task.dndquest.service.PlayCharacterService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
@Validated
public class PlayCharacterController {
    private final PlayCharacterService playCharacterService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCharacter(@Valid @RequestBody
                                PlayCharacterRequestDto playCharacterDto) {
        playCharacterService.save(playCharacterDto);
    }

    @PutMapping("/{id}/inventory")
    public ResponseEntity<List<InventoryResponseDto>> manageItem(
            @PathVariable Long id, @RequestBody List<@Valid InventoryRequestDto> items) {
        return ResponseEntity.ok(playCharacterService.manageItem(id, items));
    }

    @GetMapping("/{id}/inventory")
    public ResponseEntity<List<InventoryResponseDto>> getAllItems(@PathVariable Long id) {
        return ResponseEntity.ok(playCharacterService.getAllItems(id));
    }
}
