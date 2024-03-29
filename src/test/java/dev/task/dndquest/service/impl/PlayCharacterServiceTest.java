package dev.task.dndquest.service.impl;

import dev.task.dndquest.mapper.PlayCharacterMapper;
import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.model.entity.character.PlayCharacter;
import dev.task.dndquest.model.entity.character.PlayCharacterClass;
import dev.task.dndquest.model.entity.character.Race;
import dev.task.dndquest.repository.PlayCharacterRepository;
import dev.task.dndquest.security.authentication.AuthenticationService;
import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.service.PlayerService;
import dev.task.dndquest.service.RaceService;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayCharacterServiceTest {
    private static PlayCharacterRequestDto dto;
    private static PlayCharacterClass pcClass;
    private static Race race;
    private static PlayCharacter playCharacter;
    @Mock
    private PlayCharacterRepository repository;
    @Mock
    private PlayCharacterClassService ppcService;
    @Mock
    private RaceService raceService;
    @Mock
    private PlayCharacterMapper mapper;
    @Mock
    private AuthenticationService authService;
    @Mock
    private PlayerService playerService;
    @InjectMocks
    private PlayCharacterServiceImpl service;

    @BeforeAll
    static void init() {
        dto = new PlayCharacterRequestDto("Tom", "fighter", "orc", 10, 10, 10, 10, 10, 10);
        pcClass = new PlayCharacterClass();
        pcClass.setName("fighter");
        race = new Race();
        race.setName("orc");
        playCharacter = new PlayCharacter(
                1L, "Tom", pcClass, race, 10, 10, 10, 10, 10, 10, new HashMap<>(), null);
    }

    @Test
    void whenPlayCharacterClassParametersValid_thenReturnSavedCharacter() {
        when(mapper.mapToEntity(dto)).thenReturn(playCharacter);
        when(ppcService.findByName(pcClass.getName())).thenReturn(pcClass);
        when(raceService.findByName(race.getName())).thenReturn(race);
        when(repository.save(playCharacter)).thenReturn(playCharacter);
        when(authService.getPlayerLoginFromAuthentication()).thenReturn("Tom");
        when(playerService.addCharacterToPlayer(playCharacter, "Tom")).thenReturn(new Player());
        assertThat(service.save(dto)).isEqualTo(playCharacter);
    }

}
