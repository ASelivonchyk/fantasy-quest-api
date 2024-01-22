package dev.task.dndquest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import dev.task.dndquest.TestWebSecurityConfig;
import dev.task.dndquest.exception.CharNotFoundException;
import dev.task.dndquest.model.dto.request.InventoryRequestDto;
import dev.task.dndquest.model.dto.request.PlayCharacterRequestDto;
import dev.task.dndquest.model.dto.response.ExceptionResponseDto;
import dev.task.dndquest.model.dto.response.InventoryResponseDto;
import dev.task.dndquest.repository.PlayCharacterRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@Import(TestWebSecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-data.sql")
class PlayCharacterControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private PlayCharacterRequestDto characterRequestDto;

    @AfterAll
    static void clearDBAfterTests(@Autowired PlayCharacterRepository repository) {
        repository.deleteAll();
    }

    @BeforeEach
    public void initEach() {
        characterRequestDto = new PlayCharacterRequestDto(
                "Tom", "fighter", "orc", 10, 10, 10, 10, 10, 10);
    }

    @Test
    void whenPlayCharacterCreated_thenReturnStatusCreated_ok() {
        var dtoToSave = new PlayCharacterRequestDto(
                "Bil", "fighter", "orc", 10, 10, 10, 10, 10, 10);
        ResponseEntity<PlayCharacterRequestDto> response =
                restTemplate.postForEntity("/api/character", dtoToSave,
                        PlayCharacterRequestDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void whenPlayCharacterNameWrong_thenCreateMethodReturnStatusPreconditionFailed_notOk() {
        characterRequestDto.setName("");
        ResponseEntity<List<ExceptionResponseDto>> response =
                restTemplate.exchange("/api/character", HttpMethod.POST,
                        new HttpEntity<>(characterRequestDto),
                        new ParameterizedTypeReference<>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getMessage())
                .isEqualTo("name should be longer than 3 letter and shorter than 25");
    }

    @Test
    void whenPlayCharacterParamWrong_thenCreateMethodReturnStatusPreconditionFailed_notOk() {
        characterRequestDto.setCharisma(1);
        ResponseEntity<List<ExceptionResponseDto>> response =
                restTemplate.exchange("/api/character", HttpMethod.POST,
                        new HttpEntity<>(characterRequestDto),
                        new ParameterizedTypeReference<>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getMessage())
                .isEqualTo("character values should be greater than 5 and lower than 20");
    }

    @Test
    void whenPlayCharacterRaceWrong_thenCreateMethodReturnStatusPreconditionFailed_notOk() {
        characterRequestDto.setPlayCharRace("wrong");
        ResponseEntity<List<ExceptionResponseDto>> response =
                restTemplate.exchange("/api/character", HttpMethod.POST,
                        new HttpEntity<>(characterRequestDto),
                        new ParameterizedTypeReference<>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getMessage())
                .isEqualTo("enter valid race name");
    }

    @Test
    void whenPlayCharacterClassWrong_thenCreateMethodReturnStatusPreconditionFailed_notOk() {
        characterRequestDto.setPlayCharClass("wrong");
        ResponseEntity<List<ExceptionResponseDto>> response =
                restTemplate.exchange("/api/character", HttpMethod.POST,
                        new HttpEntity<>(characterRequestDto),
                        new ParameterizedTypeReference<>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getMessage()).isEqualTo("enter valid character class name");
    }

    @Test
    @Sql(statements = "DELETE FROM inventory WHERE character_id = 1 AND items_id = 2",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void whenCharacterExistInDBAndInventoryDtoValid_thenAddItemReturnCharactersInventoryWithChanges_ok() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("id", 1);
        var addRequestDto = List.of(new InventoryRequestDto("shield", 2, "add"),
               new InventoryRequestDto("shield", 1, "add"));
        ResponseEntity<List<InventoryResponseDto>> response =
                restTemplate.exchange("/api/character/{id}/inventory", HttpMethod.PUT,
                        new HttpEntity<>(addRequestDto),
                        new ParameterizedTypeReference<>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);
        assertThat(response.getBody()).contains(new InventoryResponseDto("shield", 4));
    }

    @Test
    @Sql(statements = "UPDATE inventory SET items_count = 1 WHERE character_id = 1 AND items_id = 1",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void whenCharacterExistInDBAndInventoryDtoValid_thenSubtractItemReturnCharactersInventoryWithChanges_ok() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("id", 1);
        var removeRequestDto = List.of(new InventoryRequestDto("sword", 1, "remove"));
        ResponseEntity<List<InventoryResponseDto>> response =
                restTemplate.exchange("/api/character/{id}/inventory", HttpMethod.PUT,
                        new HttpEntity<>(removeRequestDto),
                        new ParameterizedTypeReference<>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).contains(new InventoryResponseDto("sword", 0));
    }

    @Test
    void whenCharacterNotExistInDB_thenManageItemMethodReturnStatusPreconditionFailed_notOk() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("id", 10);
        var addRequestDto = List.of(new InventoryRequestDto("shield", 2, "add"));
        ResponseEntity<CharNotFoundException> response =
                restTemplate.exchange("/api/character/{id}/inventory", HttpMethod.PUT,
                        new HttpEntity<>(addRequestDto), CharNotFoundException.class, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("no such character in database");
    }

    @Test
    void whenSubtractResultLowerThanZero_thenSubtractItemReturnStatusBadRequest_notOk() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("id", 1);
        var removeRequestDto = List.of(new InventoryRequestDto("sword", 5, "remove"));
        ResponseEntity<IllegalArgumentException> response =
                restTemplate.exchange("/api/character/{id}/inventory", HttpMethod.PUT,
                        new HttpEntity<>(removeRequestDto),
                        IllegalArgumentException.class, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains("not enough items");
    }

    @Test
    void whenItemNotExistInDB_thenManageItemMethodReturnStatusBadRequest_notOk() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("id", 1);
        var requestDto = List.of(new InventoryRequestDto("wrongItem", 1, "add"));
        ResponseEntity<List<ExceptionResponseDto>> response =
                restTemplate.exchange("/api/character/{id}/inventory", HttpMethod.PUT,
                        new HttpEntity<>(requestDto),
                        new ParameterizedTypeReference<>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getMessage()).contains("enter valid item name");
    }

    @Test
    void whenItemOperationNotValid_thenManageItemReturnStatusPreconditionFailed_notOk() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("id", 1);
        var addRequestDto = List.of(new InventoryRequestDto("shield", 2, "wrongOperation"));
        ResponseEntity<List<ExceptionResponseDto>> response =
                restTemplate.exchange("/api/character/{id}/inventory", HttpMethod.PUT,
                        new HttpEntity<>(addRequestDto),
                        new ParameterizedTypeReference<>() {}, variable);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getMessage()).contains("enter valid item operation");
    }

    @Test
    void whenCharacterExistInDB_thenGetAllItemsReturnCharactersInventory_ok() {
        Map<String, Integer> variable = new HashMap<>();
        variable.put("id", 1);
        ResponseEntity<List<InventoryResponseDto>> response =
                restTemplate.exchange("/api/character/{id}/inventory", HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {}, variable);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);
        assertThat(response.getBody())
                .contains(new InventoryResponseDto("sword", 1))
                .contains(new InventoryResponseDto("shield", 1));
    }
}
