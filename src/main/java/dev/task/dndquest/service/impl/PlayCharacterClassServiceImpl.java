package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.ClassNotFoundException;
import dev.task.dndquest.model.entity.character.PlayCharacterClass;
import dev.task.dndquest.repository.PlayCharacterClassRepository;
import dev.task.dndquest.service.PlayCharacterClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayCharacterClassServiceImpl implements PlayCharacterClassService {
    private final PlayCharacterClassRepository repository;

    @Override
    public PlayCharacterClass findByName(String name) {
        return repository.findByName(name).orElseThrow(ClassNotFoundException::new);
    }

    @Override
    public Boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
