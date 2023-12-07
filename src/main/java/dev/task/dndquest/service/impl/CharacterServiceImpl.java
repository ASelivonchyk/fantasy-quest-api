package dev.task.dndquest.service.impl;

import dev.task.dndquest.model.Character;
import dev.task.dndquest.repository.CharacterRepository;
import dev.task.dndquest.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;

    @Override
    public Character save(Character character) {
        return repository.save(character);
    }
}
