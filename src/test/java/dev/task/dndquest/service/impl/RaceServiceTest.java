package dev.task.dndquest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import dev.task.dndquest.exception.RaceNotFoundException;
import dev.task.dndquest.model.entity.character.Race;
import dev.task.dndquest.repository.RaceRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {
    private static final String TEST_RACE_NAME = "orc";
    private static final String TEST_WRONG_RACE_NAME = "notExist";
    private static Race existingRaceInDB;
    @Mock
    private RaceRepository repository;
    @InjectMocks
    private RaceServiceImpl raceService;

    @BeforeAll
    static void init(){
        existingRaceInDB = new Race();
        existingRaceInDB.setName(TEST_RACE_NAME);
        existingRaceInDB.setId(1L);
    }

    @Test
    void whenRaceExistInDb_thenReturnRace() {
        when(repository.findByName(TEST_RACE_NAME)).thenReturn(Optional.of(existingRaceInDB));
        assertThat(raceService.findByName(TEST_RACE_NAME)).isEqualTo(existingRaceInDB);
    }

    @Test
    void whenRaceNotExistInDb_thenThrowRaceNotFoundException() {
        when(repository.findByName(TEST_WRONG_RACE_NAME)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> raceService.findByName(TEST_WRONG_RACE_NAME))
                .isInstanceOf(RaceNotFoundException.class);
    }

    @Test
    void whenRaceExistsInDb_thenReturnTrue() {
        when(repository.existsByName(TEST_RACE_NAME)).thenReturn(true);
        assertThat(raceService.existsByName(TEST_RACE_NAME)).isTrue();
    }

    @Test
    void whenRaceNotExistInDb_thenReturnFalse() {
        when(repository.existsByName(TEST_WRONG_RACE_NAME)).thenReturn(false);
        assertThat(raceService.existsByName(TEST_WRONG_RACE_NAME)).isFalse();
    }
}
