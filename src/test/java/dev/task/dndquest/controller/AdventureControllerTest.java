package dev.task.dndquest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.model.dto.response.ExceptionResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.model.entity.adventure.StoryLine;
import dev.task.dndquest.repository.StoryLineRepository;
import dev.task.dndquest.security.authentication.AuthenticationService;
import dev.task.dndquest.service.PlayerService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdventureControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private MultiValueMap<String, String> authMap;

    @BeforeAll
    @CacheEvict(value = "ps", allEntries = true)
    void init(@Autowired AuthenticationService authService,
              @Autowired PlayerService playerService) {
        PlayerRequestDto dto = new PlayerRequestDto("TestUser", "1qwerty2");
        playerService.save(dto);
        authMap = new LinkedMultiValueMap<>();
        authMap.put("Authorization", List.of("Bearer " + authService.login(dto).getToken()));
    }

    @Test
    @Order(1)
    void getAvailableAdventures_returnListOfStoryLineShortResponseDto_ok() {
        var response = restTemplate.exchange(
                "/api/adventure/available", HttpMethod.GET,
                new HttpEntity<>(null, authMap),
                new ParameterizedTypeReference<List<StoryLineShortResponseDto>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(3);
        assertThat(response.getBody().get(0).getTitle()).isNotNull();
        assertThat(response.getBody().get(0).getDescription()).isNotNull();
        assertThat(response.getBody().get(0).getSerial()).isNotNull();
    }

    @Test
    @Order(2)
    void addAvailableAdventures_addingListOfStoryLineShortResponseDto_ok() {
        var response = restTemplate.exchange(
                "/api/adventure/available/new", HttpMethod.GET,
                new HttpEntity<>(null, authMap),
                new ParameterizedTypeReference<List<StoryLineShortResponseDto>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(6);
        assertThat(response.getBody().get(5).getTitle()).isNotNull();
        assertThat(response.getBody().get(5).getDescription()).isNotNull();
        assertThat(response.getBody().get(5).getSerial()).isNotNull();
    }

    @Test
    @Order(3)
    void whenSerialOfAdventureIsValid_thenSelectAdventureReturnStoryLineFullResponseDto_ok() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 1);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.GET,
                new HttpEntity<>(null, authMap),
                new ParameterizedTypeReference<StoryLineFullResponseDto>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @Order(4)
    void whenSerialOfAdventureIsNotValid_thenSelectAdventureReturnServiceUnavailable_notOk() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 0);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.GET,
                new HttpEntity<>(null, authMap),
                new ParameterizedTypeReference<ExceptionResponseDto>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("no such story line");
    }

    @Test
    @Order(5)
    void whenSerialOfAdventureIsNotValid_thenStartAdventureSaveStoryLineFToDb_ok(
            @Autowired StoryLineRepository repository) {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 1);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.POST,
                new HttpEntity<>(null, authMap),
                new ParameterizedTypeReference<String>() {}, variable);
        StoryLine storyLine = repository.findAll().get(0);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(storyLine.getDescription()).isNotNull();
        assertThat(storyLine.getTitle()).isNotNull();
    }

    @Test
    @Order(6)
    void whenSerialOfAdventureIsNotValid_thenStartAdventureReturnServiceUnavailable_notOk() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 0);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.POST,
                new HttpEntity<>(null, authMap),
                new ParameterizedTypeReference<ExceptionResponseDto>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("no such story line");
    }
}
