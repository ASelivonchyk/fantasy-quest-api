package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.DBException;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.model.entity.PlayCharacterClass;
import dev.task.dndquest.model.entity.Race;
import dev.task.dndquest.repository.PlayCharacterClassRepository;
import dev.task.dndquest.repository.PlayCharacterRepository;
import dev.task.dndquest.repository.RaceRepository;
import dev.task.dndquest.service.PlayCharacterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayCharacterServiceImpl implements PlayCharacterService {
    private final PlayCharacterRepository repository;
    private final PlayCharacterClassRepository pccRepository;
    private final RaceRepository raceRepository;

    @Override
    public PlayCharacter save(PlayCharacter playCharacter) {
        PlayCharacterClass characterClass = pccRepository.findByName(playCharacter.getClas().getName())
                .orElseThrow(() -> new DBException("no such character class in database"));
        Race race = raceRepository.findByName(playCharacter.getRace().getName())
                .orElseThrow(() -> new DBException("no such race in database"));
        playCharacter.setClas(characterClass);
        playCharacter.setRace(race);
        return repository.save(playCharacter);
    }
}
