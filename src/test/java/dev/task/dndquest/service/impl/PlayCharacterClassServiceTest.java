package dev.task.dndquest.service.impl;

import java.util.Optional;
import dev.task.dndquest.exception.CharacterClassNotFoundException;
import dev.task.dndquest.model.entity.character.PlayCharacterClass;
import dev.task.dndquest.repository.PlayCharacterClassRepository;
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
class PlayCharacterClassServiceTest {
    private static final String TEST_CLASS_NAME = "fighter";
    private static final String TEST_WRONG_CLASS_NAME = "notExist";
    @Mock
    private PlayCharacterClassRepository repository;
    @InjectMocks
    private  PlayCharacterClassServiceImpl playCharacterClassService;
    private static PlayCharacterClass existingClassInDB;

    @BeforeAll
    static void init(){
        existingClassInDB = new PlayCharacterClass();
        existingClassInDB.setName(TEST_CLASS_NAME);
        existingClassInDB.setId(1L);
    }
    @Test
    void whenClassExistInDB_thenReturnClass_ok() {
        when(repository.findByName(TEST_CLASS_NAME)).thenReturn(Optional.of(existingClassInDB));
        assertThat(playCharacterClassService.findByName(TEST_CLASS_NAME)).isEqualTo(existingClassInDB);
    }

    @Test
    void whenClassNotExistInDB_thenThrowException_notOk() {
        when(repository.findByName(TEST_WRONG_CLASS_NAME)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> playCharacterClassService.findByName(TEST_WRONG_CLASS_NAME))
                .isInstanceOf(CharacterClassNotFoundException.class);
    }

    @Test
    void whenClassExistsInDB_thenReturnTrue_ok() {
        when(repository.existsByName(TEST_CLASS_NAME)).thenReturn(true);
        assertThat(playCharacterClassService.existsByName(TEST_CLASS_NAME)).isTrue();
    }

    @Test
    void whenClassNotExistInDB_thenReturnFalse_ok() {
        when(repository.existsByName(TEST_WRONG_CLASS_NAME)).thenReturn(false);
        assertThat(playCharacterClassService.existsByName(TEST_WRONG_CLASS_NAME)).isFalse();
    }
}
