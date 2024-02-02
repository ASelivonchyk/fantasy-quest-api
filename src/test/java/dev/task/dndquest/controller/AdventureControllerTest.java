package dev.task.dndquest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.ExceptionResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineFullResponseDto;
import dev.task.dndquest.model.dto.response.StoryLineShortResponseDto;
import dev.task.dndquest.model.entity.character.PlayCharacter;
import dev.task.dndquest.service.PlayCharacterService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdventureControllerTest extends GenericControllerTest {
    @Test
    @Order(1)
    void whenRequestValid_thenReturnListOfStoryLineShortResponseDto() {
        var response = restTemplate.exchange(
                "/api/adventure/available", HttpMethod.GET,
                new HttpEntity<>(null, jwt),
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
    void whenRequestValid_thenReturnListOfStoryLineShortResponseDtoWithAddedData() {
        var response = restTemplate.exchange(
                "/api/adventure/available/new", HttpMethod.GET,
                new HttpEntity<>(null, jwt),
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
    void whenSerialOfAdventureIsValid_thenReturnStoryLineFullResponseDto() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 1);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.GET,
                new HttpEntity<>(null, jwt),
                new ParameterizedTypeReference<StoryLineFullResponseDto>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @Order(4)
    void whenSerialOfAdventureInGetRequestIsNotValid_thenReturnServiceUnavailable() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 0);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.GET,
                new HttpEntity<>(null, jwt),
                new ParameterizedTypeReference<ExceptionResponseDto>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("no such story line");
    }

    @Test
    @Order(5)
    void whenSerialOfAdventureIsValid_thenSaveAdventureToDbAndReturnStatusOk(
            @Autowired PlayCharacterService characterService) {
        PlayCharacter savedCharacter = characterService.save(
                new PlayCharacterRequestDto("Bil", "fighter", "orc", 10, 10, 10, 10, 10, 10));
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 1);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.POST,
                new HttpEntity<>(null, jwt),
                new ParameterizedTypeReference<String>() {}, variable);
        PlayCharacter character = characterService.findById(savedCharacter.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(character.getAdventure()).isNotNull();
        assertThat(character.getAdventure().getStoryLine()).isNotNull();
        assertThat(character.getAdventure().getStoryLine().getDescription()).isNotNull();
        assertThat(character.getAdventure().getStoryLine().getDescription()).isNotNull();
        assertThat(character.getAdventure().getStoryLine().getStories()).isNotNull();
        assertThat(character.getAdventure().getStoryLine().getStories().size()).isEqualTo(4);
        assertThat(character.getAdventure().getStoryLine().getStories().get(0).getStory()).isNotNull();
        assertThat(character.getAdventure().getStoryLine().getStories().get(0).getDescription()).isNotNull();
    }

    @Test
    @Order(6)
    void whenSerialOfAdventureInPostRequestIsNotValid_thenReturnServiceUnavailable() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("serial", 0);
        var response = restTemplate.exchange(
                "/api/adventure/available/{serial}", HttpMethod.POST,
                new HttpEntity<>(null, jwt),
                new ParameterizedTypeReference<ExceptionResponseDto>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("no such story line");
    }
}
