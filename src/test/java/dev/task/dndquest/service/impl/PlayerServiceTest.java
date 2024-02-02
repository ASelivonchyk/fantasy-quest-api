package dev.task.dndquest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import dev.task.dndquest.exception.BadCredentialsException;
import dev.task.dndquest.exception.DuplicateLoginException;
import dev.task.dndquest.mapper.PlayerMapper;
import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.model.entity.character.PlayCharacter;
import dev.task.dndquest.repository.PlayerRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    private static final String TEST_LOGIN = "Tom";
    private static final String TEST_PASS = "qwerty12";
    private static final String ENCODED_PASSWORD = "encodedPassword";
    private static PlayerRequestDto dto;
    private static Player player;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    private PlayerRepository repository;
    @Mock
    private PlayerMapper mapper;
    @InjectMocks
    private PlayerServiceImpl service;

    @BeforeAll
    static void init() {
        dto = new PlayerRequestDto(TEST_LOGIN, TEST_PASS);
        player = new Player(1L, TEST_LOGIN, TEST_PASS, null);
    }

    @Test
    void whenPlayerParametersValid_thenReturnSavedPlayer() {
        when(repository.existsByLogin(dto.getLogin())).thenReturn(false);
        when(mapper.mapToEntity(dto)).thenReturn(player);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn(ENCODED_PASSWORD);
        when(repository.save(player)).thenReturn(player);
        assertThat(service.save(dto)).isEqualTo(player);
    }

    @Test
    void whenPlayerExistInDb_thenThrowDuplicateLoginException() {
        when(repository.existsByLogin(dto.getLogin())).thenReturn(true);
        assertThatThrownBy(() -> service.save(dto)).isInstanceOf(DuplicateLoginException.class);
    }

    @Test
    void whenPlayerExistInDb_thenReturnPlayer() {
        when(repository.findByLogin(TEST_LOGIN)).thenReturn(Optional.of(player));
        assertThat(service.findByLogin(TEST_LOGIN)).isEqualTo(player);
    }

    @Test
    void whenPlayerNotExistInDb_thenThrowBadCredentialsException() {
        when(repository.findByLogin(TEST_LOGIN)).thenReturn(Optional.empty());
        assertThatThrownBy(() ->
                service.findByLogin(TEST_LOGIN)).isInstanceOf(BadCredentialsException.class);
    }

    @Test
    void whenPlayerExistsInDb_thenReturnTrue() {
        when(repository.existsByLogin(TEST_LOGIN)).thenReturn(true);
        assertThat(service.existsByLogin(TEST_LOGIN)).isTrue();
    }

    @Test
    void whenPlayerNotExistsInDb_thenReturnFalse() {
        when(repository.existsByLogin(TEST_LOGIN)).thenReturn(false);
        assertThat(service.existsByLogin(TEST_LOGIN)).isFalse();
    }

    @Test
    void whenPlayCharacterValidButPlayerNotExistIDb_thenThrowBadCredentialsException() {
        assertThatThrownBy(() ->
                service.addCharacterToPlayer(new PlayCharacter(), TEST_LOGIN))
                .isInstanceOf(BadCredentialsException.class);
    }
}
