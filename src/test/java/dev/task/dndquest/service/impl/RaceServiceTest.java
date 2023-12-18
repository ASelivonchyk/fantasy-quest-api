package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.RaceNotFoundException;
import dev.task.dndquest.model.entity.Race;
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
    @Mock
    private RaceRepository repository;
    @InjectMocks
    private RaceServiceImpl raceService;
    private static Race existedRaceInDB;

    @BeforeAll
    static void init(){
        existedRaceInDB = new Race();
        existedRaceInDB.setName("orc");
        existedRaceInDB.setId(1L);
    }

    @Test
    void whenRaceExistInDB_thenReturnRace_ok() {
        when(repository.findByName("orc")).thenReturn(Optional.of(existedRaceInDB));
        assertThat(raceService.findByName("orc")).isEqualTo(existedRaceInDB);
    }

    @Test
    void whenRaceNotExistInDB_thenThrowException_notOk() {
        when(repository.findByName("notExist")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> raceService.findByName("notExist")).isInstanceOf(RaceNotFoundException.class);
    }

    @Test
    void whenRaceExistsInDB_thenReturnTrue_ok() {
        when(repository.existsByName("orc")).thenReturn(true);
        assertThat(raceService.existsByName("orc")).isTrue();
    }

    @Test
    void whenRaceNotExistInDB_thenReturnFalse_ok() {
        when(repository.existsByName("notExist")).thenReturn(false);
        assertThat(raceService.existsByName("notExist")).isFalse();
    }
}
