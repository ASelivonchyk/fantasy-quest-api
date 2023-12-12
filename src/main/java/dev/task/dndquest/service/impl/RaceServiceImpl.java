package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.RaceNotFoundException;
import dev.task.dndquest.model.entity.Race;
import dev.task.dndquest.repository.RaceRepository;
import dev.task.dndquest.service.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RaceServiceImpl implements RaceService {
    private final RaceRepository repository;

    @Override
    public Race findByName(String name) {
        return repository.findByName(name).orElseThrow(RaceNotFoundException::new);
    }

    @Override
    public Boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
