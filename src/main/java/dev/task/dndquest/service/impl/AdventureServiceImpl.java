package dev.task.dndquest.service.impl;

import dev.task.dndquest.mapper.AdventureMapper;
import dev.task.dndquest.model.dto.request.AdventureRequestDto;
import dev.task.dndquest.model.entity.adventure.Adventure;
import dev.task.dndquest.repository.AdventureRepository;
import dev.task.dndquest.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdventureServiceImpl implements AdventureService {
    private final AdventureMapper mapper;
    private final AdventureRepository repository;

    @Override
    public Adventure save(AdventureRequestDto dto) {
        Adventure adventure = mapper.mapToEntity(dto);
        Adventure save = repository.save(adventure);
        return save;
    }
}
