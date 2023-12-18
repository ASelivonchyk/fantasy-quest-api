package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.PlayCharacterClass;
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
    @Autowired
    private PlayCharacterClassRepository repository;
    private static PlayCharacterClass existedClassInDB;

    @BeforeAll
    static void init(){
        existedClassInDB = new PlayCharacterClass();
        existedClassInDB.setName("fighter");
        existedClassInDB.setId(1L);
    }

    @Test
    void whenClassExistInDB_thenReturnOptionalWithClass_ok() {
        assertThat(repository.findByName("fighter")).contains(existedClassInDB);
    }

    @Test
    void whenClassNotExistInDB_thenReturnEmptyOptional_ok() {
        assertThat(repository.findByName("notExist")).isEmpty();
    }

    @Test
    void whenClassExistsInDB_thenReturnTrue_ok() {
        assertThat(repository.existsByName("fighter")).isTrue();
    }

    @Test
    void whenClassNotExistInDB_thenReturnFalse_ok() {
        assertThat(repository.existsByName("notExist")).isFalse();
    }
}