package dev.task.dndquest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import dev.task.dndquest.model.entity.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerRepositoryTest {
    private static final String TEST_LOGIN = "Tim";
    private static final String TEST_WRONG_LOGIN = "notExist";
    private static final String TEST_PASSWORD = "qwertyu1";
    private Player playerFromDB;
    @Autowired
    private PlayerRepository repository;

    @BeforeAll
    public void setUp(){
        playerFromDB = new Player(null, TEST_LOGIN, TEST_PASSWORD, null);
        playerFromDB = repository.save(playerFromDB);
    }

    @AfterAll
    void clearDbAfterTests() {
        repository.deleteAll();
    }

    @Test
    void whenPlayerParametersValid_thenReturnSavedPlayer() {
        Player testPlayer = new Player(null, "Bob", TEST_PASSWORD, null);
        assertThat(repository.save(testPlayer).getId()).isNotNull();
    }

    @Test
    void whenPlayerExistInDB_thenReturnOptionalWithPlayer() {
        assertThat(repository.findByLogin(TEST_LOGIN)).contains(playerFromDB);
    }

    @Test
    void whenPlayerNotExistInDB_thenReturnEmptyOptional() {
        assertThat(repository.findByLogin(TEST_WRONG_LOGIN)).isEmpty();
    }

    @Test
    void whenPlayerExistsInDB_thenReturnTrue() {
        assertThat(repository.existsByLogin(TEST_LOGIN)).isTrue();
    }

    @Test
    void whenPlayerNotExistInDB_thenReturnFalse() {
        assertThat(repository.existsByLogin(TEST_WRONG_LOGIN)).isFalse();
    }
}
