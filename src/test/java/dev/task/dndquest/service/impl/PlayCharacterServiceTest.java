package dev.task.dndquest.service.impl;

import dev.task.dndquest.mapper.DtoMapper;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.PlayCharacterResponseDto;
import dev.task.dndquest.model.entity.PlayCharacter;
import dev.task.dndquest.model.entity.PlayCharacterClass;
import dev.task.dndquest.model.entity.Race;
import dev.task.dndquest.repository.PlayCharacterRepository;
import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.service.RaceService;
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
    @Mock
    private PlayCharacterRepository repository;
    @Mock
    private PlayCharacterClassService ppcService;
    @Mock
    private RaceService raceService;
    @Mock
    DtoMapper<PlayCharacter,
            PlayCharacterResponseDto, PlayCharacterRequestDto> mapper;
    @InjectMocks
    private PlayCharacterServiceImpl service;
    private static PlayCharacterRequestDto dto;
    private static PlayCharacterClass pcClass;
    private static Race race;
    private static PlayCharacter playCharacter;

    @BeforeAll
    static void init(){
        dto = new PlayCharacterRequestDto("Tom", "fighter", "orc", 10, 10, 10, 10, 10, 10);
        pcClass = new PlayCharacterClass();
        pcClass.setName("fighter");
        race = new Race();
        race.setName("orc");
        playCharacter = new PlayCharacter(1L, "Tom", pcClass, race, 10, 10, 10, 10, 10, 10);
    }
    @Test
    void savePlayCharacterClass_ok() {
        when(mapper.mapToEntity(dto)).thenReturn(playCharacter);
        when(ppcService.findByName(pcClass.getName())).thenReturn(pcClass);
        when(raceService.findByName(race.getName())).thenReturn(race);
        when(repository.save(playCharacter)).thenReturn(playCharacter);
        assertThat(service.save(dto)).isEqualTo(playCharacter);
    }
}
