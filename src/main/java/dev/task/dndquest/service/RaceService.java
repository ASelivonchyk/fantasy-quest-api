package dev.task.dndquest.service;

import dev.task.dndquest.model.entity.character.Race;

public interface RaceService {
    Race findByName(String name);
    Boolean existsByName(String name);
}
