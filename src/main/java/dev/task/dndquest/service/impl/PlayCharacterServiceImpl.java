package dev.task.dndquest.service.impl;

import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.repository.PlayCharacterRepository;
import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.service.PlayCharacterService;
import dev.task.dndquest.service.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayCharacterServiceImpl implements PlayCharacterService {
    private final PlayCharacterRepository repository;
    private final PlayCharacterClassService pccService;
    private final RaceService raceService;

    @Override
    public PlayCharacter save(PlayCharacter playCharacter) {
        playCharacter.setClas(pccService.findByName(playCharacter.getClas().getName()));
        playCharacter.setRace(raceService.findByName(playCharacter.getRace().getName()));
        return repository.save(playCharacter);
    }
}
