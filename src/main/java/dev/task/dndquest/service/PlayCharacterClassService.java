package dev.task.dndquest.service;

import dev.task.dndquest.model.entity.PlayCharacterClass;

public interface PlayCharacterClassService {
    PlayCharacterClass findByName(String name);
    Boolean existsByName(String name);
}
