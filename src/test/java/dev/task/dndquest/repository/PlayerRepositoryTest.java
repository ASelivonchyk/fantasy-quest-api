package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.Player;
import org.junit.jupiter.api.AfterAll;
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
    private static final String TEST_LOGIN = "Tim";
    private static final String TEST_WRONG_LOGIN = "notExist";
    private static final String TEST_PASSWORD = "qwertyu1";

    @Autowired
    private PlayerRepository repository;
    private Player playerFromDB;

    @BeforeAll
    public void setUp(){
        playerFromDB = new Player(null, TEST_LOGIN, TEST_PASSWORD);
        playerFromDB = repository.save(playerFromDB);
    }

    @AfterAll
    void clearDBAfterTests() {
        repository.deleteAll();
    }

    @Test
    void whenSaveToDB_thenReturnPlayer_ok() {
        Player testPlayer = new Player(null, "Bob", TEST_PASSWORD);
        assertThat(repository.save(testPlayer).getId()).isNotNull();
    }

    @Test
    void whenPlayerExistInDB_thenFindByLoginReturnOptionalWithPlayer_ok() {
        assertThat(repository.findByLogin(TEST_LOGIN)).contains(playerFromDB);
    }

    @Test
    void whenPlayerNotExistInDB_thenFindByLoginReturnEmptyOptional_ok() {
        assertThat(repository.findByLogin(TEST_WRONG_LOGIN)).isEmpty();
    }

    @Test
    void whenPlayerExistsInDB_thenExistsByLoginReturnTrue_ok() {
        assertThat(repository.existsByLogin(TEST_LOGIN)).isTrue();
    }

    @Test
    void whenPlayerNotExistInDB_thenExistsByLoginReturnFalse_ok() {
        assertThat(repository.existsByLogin(TEST_WRONG_LOGIN)).isFalse();
    }
}
