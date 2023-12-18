package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.Race;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class RaceRepositoryTest {
    @Autowired
    private RaceRepository repository;
    private static Race existedRaceInDB;

    @BeforeAll
    static void init(){
        existedRaceInDB = new Race();
        existedRaceInDB.setName("orc");
        existedRaceInDB.setId(1L);
    }

    @Test
    void saveRaceToDb_ok(){
        Race race = new Race();
        race.setName("goblin");
        assertThat(repository.save(race)).isEqualTo(race);
    }

    @Test
    void whenRaceExistInDB_thenReturnOptionalWithRace_ok() {
        System.out.println(repository.findAll());
        assertThat(repository.findByName("orc")).contains(existedRaceInDB);
    }

    @Test
    void whenRaceNotExistInDB_thenReturnEmptyOptional_ok() {
        assertThat(repository.findByName("notExist")).isEmpty();
    }

    @Test
    void whenRaceExistsInDB_thenReturnTrue_ok() {
        assertThat(repository.existsByName("orc")).isTrue();
    }

    @Test
    void whenRaceNotExistInDB_thenReturnFalse_ok() {
        assertThat(repository.existsByName("notExist")).isFalse();
    }
}
