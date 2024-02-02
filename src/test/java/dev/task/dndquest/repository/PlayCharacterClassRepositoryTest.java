package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.character.PlayCharacterClass;
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
class PlayCharacterClassRepositoryTest {
    private static final String TEST_CLASS_NAME = "fighter";
    private static final String TEST_WRONG_CLASS_NAME = "notExist";
    private static PlayCharacterClass existingClassInDB;
    @Autowired
    private PlayCharacterClassRepository repository;

    @BeforeAll
    static void init(){
        existingClassInDB = new PlayCharacterClass();
        existingClassInDB.setName(TEST_CLASS_NAME);
        existingClassInDB.setId(1L);
    }

    @Test
    void whenClassExistInDB_thenReturnOptionalWithClass() {
        assertThat(repository.findByName(TEST_CLASS_NAME)).contains(existingClassInDB);
    }

    @Test
    void whenClassNotExistInDB_thenReturnEmptyOptional() {
        assertThat(repository.findByName(TEST_WRONG_CLASS_NAME)).isEmpty();
    }

    @Test
    void whenClassExistsInDB_thenReturnTrue() {
        assertThat(repository.existsByName(TEST_CLASS_NAME)).isTrue();
    }

    @Test
    void whenClassNotExistInDB_thenReturnFalse() {
        assertThat(repository.existsByName(TEST_WRONG_CLASS_NAME)).isFalse();
    }
}
