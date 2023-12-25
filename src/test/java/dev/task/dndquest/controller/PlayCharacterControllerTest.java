package dev.task.dndquest.controller;

import dev.task.dndquest.TestWebSecurityConfig;
import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestWebSecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class PlayCharacterControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private PlayCharacterRequestDto dto;

    @BeforeEach
    public void initEach(){
        dto = new PlayCharacterRequestDto("Tom","fighter","orc",10, 10, 10, 10, 10, 10);
    }

    @Test
    void whenPlayCharacterCreated_thenReturnStatusCreated_ok() {
        ResponseEntity<PlayCharacterRequestDto> response =
                restTemplate.postForEntity("/api/character", dto, PlayCharacterRequestDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void whenPlayCharacterNameWrong_thenReturnStatusPreconditionFailed_notOk(){
        dto.setName("");
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/character", dto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void whenPlayCharacterParamWrong_thenReturnStatusPreconditionFailed_notOk(){
        dto.setCharisma(1);
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/character", dto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void whenPlayCharacterRaceWrong_thenReturnStatusPreconditionFailed_notOk(){
        dto.setPlayCharRace("wrong");
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/character", dto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void whenPlayCharacterClassWrong_thenReturnStatusPreconditionFailed_notOk(){
        dto.setPlayCharClass("wrong");
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/character", dto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
    }
}
