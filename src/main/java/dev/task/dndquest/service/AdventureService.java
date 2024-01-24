package dev.task.dndquest.service;

import dev.task.dndquest.model.dto.request.AdventureRequestDto;
import dev.task.dndquest.model.entity.adventure.Adventure;

public interface AdventureService {
    Adventure save(AdventureRequestDto dto);
}
