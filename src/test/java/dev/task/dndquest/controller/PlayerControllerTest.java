package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.PlayerRequestDto;
import dev.task.dndquest.model.entity.Player;
import dev.task.dndquest.service.PlayerService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PlayerService service;
    private static PlayerRequestDto dtoToSave;
    private static Player player;

    @BeforeAll
    void setUp(){
        dtoToSave = new PlayerRequestDto("Tom", "1qwerty2");
        player = service.save(dtoToSave);
    }

    @Test
    void register() {
        ResponseEntity<PlayerRequestDto> response =
                restTemplate.postForEntity("/api/player/register",
                        new PlayerRequestDto("Bob", "1qwerty2"),
                        PlayerRequestDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void login() {
        ResponseEntity<Object> response =
                restTemplate.postForEntity("/api/player/login",
                        dtoToSave, Object.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
