package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.BadCredentialsException;
import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.model.dto.PlayerResponseDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.repository.PlayerRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    private PlayerRepository repository;
    @Mock
    private DtoMapper<Player,
            PlayerResponseDto, PlayerRequestDto> mapper;
    @InjectMocks
    private PlayerServiceImpl service;
    private static PlayerRequestDto dto;
    private static Player player;
    private static String encodedPassword;

    @BeforeAll
    static void init() {
        dto = new PlayerRequestDto("Tom", "qwerty12");
        player = new Player(1L, "Tom", "qwerty12");
        encodedPassword = "encodedPassword";
    }

    @Test
    void whenSavePlayer_thenReturnPlayer_ok() {
        when(mapper.mapToEntity(dto)).thenReturn(player);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn(encodedPassword);
        when(repository.save(player)).thenReturn(player);
        assertThat(service.save(dto)).isEqualTo(player);
    }

    @Test
    void whenFindByLoginAndPlayerExistInDB_thenReturnPlayer_ok() {
        when(repository.findByLogin("Tom")).thenReturn(Optional.of(player));
        assertThat(service.findByLogin("Tom")).isEqualTo(player);
    }

    @Test
    void whenFindByLoginAndPlayerNotExistInDB_thenThrowException_notOk() {
        when(repository.findByLogin("notExist")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findByLogin("notExist")).isInstanceOf(BadCredentialsException.class);
    }

    @Test
    void whenExistByLoginAndPlayerExistsInDB_thenReturnTrue_ok() {
        when(repository.existsByLogin("Tom")).thenReturn(true);
        assertThat(service.existsByLogin("Tom")).isTrue();
    }

    @Test
    void whenExistByLoginAndPlayerNotExistsInDB_thenReturnFalse_ok() {
        when(repository.existsByLogin("notExist")).thenReturn(false);
        assertThat(service.existsByLogin("notExist")).isFalse();
    }
}
