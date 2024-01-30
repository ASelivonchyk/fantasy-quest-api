package dev.task.dndquest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import dev.task.dndquest.model.entity.character.Race;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class RaceRepositoryTest {
    private static final String TEST_RACE_NAME = "orc";
    private static final String TEST_WRONG_RACE_NAME = "notExist";
    private static Race existingRaceInDB;
    @Autowired
    private RaceRepository repository;

    @BeforeAll
    static void init(){
        existingRaceInDB = new Race();
        existingRaceInDB.setName(TEST_RACE_NAME);
        existingRaceInDB.setId(1L);
    }

    @Test
    void  whenRaceParametersValid_thenReturnSavedRace() {
        Race race = new Race();
        race.setName("goblin");
        assertThat(repository.save(race)).isEqualTo(race);
    }

    @Test
    void whenRaceExistInDb_thenReturnOptionalWithRace() {
        assertThat(repository.findByName(TEST_RACE_NAME)).contains(existingRaceInDB);
    }

    @Test
    void whenRaceNotExistInDb_thenReturnEmptyOptional() {
        assertThat(repository.findByName(TEST_WRONG_RACE_NAME)).isEmpty();
    }

    @Test
    void whenRaceExistsInDb_thenExistByNameReturnTrue() {
        assertThat(repository.existsByName(TEST_RACE_NAME)).isTrue();
    }

    @Test
    void whenRaceNotExistInDb_thenExistByNameReturnFalse() {
        assertThat(repository.existsByName(TEST_WRONG_RACE_NAME)).isFalse();
    }
}
