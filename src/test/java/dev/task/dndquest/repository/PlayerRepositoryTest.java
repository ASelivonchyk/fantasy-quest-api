package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository repository;
    private Player playerFromDB;

    @BeforeAll
    public void setUp(){
        playerFromDB = new Player("Tim", "qwertyu1");
        playerFromDB = repository.save(playerFromDB);
    }

    @Test
    void whenSaveToDB_thenReturnPlayer_ok() {
        Player testPlayer = new Player("Bob", "1qwerty2");
        assertThat(repository.save(testPlayer).getId()).isEqualTo(2L);
    }

    @Test
    void whenPlayerExistInDB_thenFindByLoginReturnOptionalWithPlayer_ok() {
        assertThat(repository.findByLogin("Tim")).contains(playerFromDB);
    }

    @Test
    void whenPlayerNotExistInDB_thenFindByLoginReturnEmptyOptional_ok() {
        assertThat(repository.findByLogin("notExist")).isEmpty();
    }

    @Test
    void whenPlayerExistsInDB_thenExistsByLoginReturnTrue_ok() {
        assertThat(repository.existsByLogin("Tim")).isTrue();
    }

    @Test
    void whenPlayerNotExistInDB_thenExistsByLoginReturnFalse_ok() {
        assertThat(repository.existsByLogin("notExist")).isFalse();
    }
}
