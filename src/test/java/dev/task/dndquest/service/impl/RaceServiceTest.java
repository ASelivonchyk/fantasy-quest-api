package dev.task.dndquest.service.impl;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {
    private static final String TEST_RACE_NAME = "orc";
    private static final String TEST_WRONG_RACE_NAME = "notExist";
    @Mock
    private RaceRepository repository;
    @InjectMocks
    private RaceServiceImpl raceService;
    private static Race existingRaceInDB;

    @BeforeAll
    static void init(){
        existingRaceInDB = new Race();
        existingRaceInDB.setName(TEST_RACE_NAME);
        existingRaceInDB.setId(1L);
    }

    @Test
    void whenRaceExistInDB_thenReturnRace_ok() {
        when(repository.findByName(TEST_RACE_NAME)).thenReturn(Optional.of(existingRaceInDB));
        assertThat(raceService.findByName(TEST_RACE_NAME)).isEqualTo(existingRaceInDB);
    }

    @Test
    void whenRaceNotExistInDB_thenThrowException_notOk() {
        when(repository.findByName(TEST_WRONG_RACE_NAME)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> raceService.findByName(TEST_WRONG_RACE_NAME))
                .isInstanceOf(RaceNotFoundException.class);
    }

    @Test
    void whenRaceExistsInDB_thenReturnTrue_ok() {
        when(repository.existsByName(TEST_RACE_NAME)).thenReturn(true);
        assertThat(raceService.existsByName(TEST_RACE_NAME)).isTrue();
    }

    @Test
    void whenRaceNotExistInDB_thenReturnFalse_ok() {
        when(repository.existsByName(TEST_WRONG_RACE_NAME)).thenReturn(false);
        assertThat(raceService.existsByName(TEST_WRONG_RACE_NAME)).isFalse();
    }
}
