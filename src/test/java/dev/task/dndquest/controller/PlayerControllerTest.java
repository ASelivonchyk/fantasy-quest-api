package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.repository.PlayerRepository;
import dev.task.dndquest.service.PlayerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerControllerTest {
    private static final String PLAYER_IN_DB_LOGIN = "Tom";
    private static final String PLAYER_PASS = "1qwerty2";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PlayerService service;
    private PlayerRequestDto dtoToSave;
    private Player player;

    @BeforeAll
    void setUp(){
        dtoToSave = new PlayerRequestDto(PLAYER_IN_DB_LOGIN, PLAYER_PASS);
        player = service.save(dtoToSave);
    }

    @AfterAll
    void clearDBAfterTests(@Autowired PlayerRepository repository) {
        repository.deleteAll();
    }

    @Test
    void whenRegisterPlayerParamValid_thenReturnStatusCreated_ok() {
        ResponseEntity<PlayerRequestDto> response =
                restTemplate.postForEntity("/api/player/register",
                        new PlayerRequestDto("Bob", PLAYER_PASS),
                        PlayerRequestDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void whenPlayerExistInDB_thenReturnStatusBadRequest_notOk(){
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/player/register", dtoToSave, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void whenLoginPlayerParamValid_thenReturnJwtAndStatusOk_ok() {
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/player/login",
                        dtoToSave, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void whenLoginIncorrect_thenReturnStatusBadRequest_notOk() {
        PlayerRequestDto dto = new PlayerRequestDto("wrong", PLAYER_PASS);
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/player/login",
                        dto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void whenPasswordIncorrect_thenReturnStatusPreconditionFailed_notOk() {
        PlayerRequestDto dto = new PlayerRequestDto(PLAYER_IN_DB_LOGIN, "wrong");
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/player/login",
                        dto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
    }
}
