package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.CharNotFoundException;
import dev.task.dndquest.model.entity.PlayCharacterClass;
import dev.task.dndquest.repository.PlayCharacterClassRepository;
import dev.task.dndquest.service.PlayCharacterClassService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayCharacterClassServiceImpl implements PlayCharacterClassService {
    private final PlayCharacterClassRepository repository;

    @Override
    public PlayCharacterClass findByName(String name) {
        return repository.findByName(name).orElseThrow(CharNotFoundException::new);
    }

    @Override
    public Boolean existsByName(String name) {
       return repository.existsByName(name);
    }
}
