package dev.task.dndquest.controller;

import dev.task.dndquest.model.dto.request.PlayerRequestDto;
import dev.task.dndquest.repository.PlayerRepository;
import dev.task.dndquest.security.authentication.AuthenticationService;
import dev.task.dndquest.service.PlayerService;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class GenericControllerTest {
    private static final String PLAYER_LOGIN = "TestUser";
    private static final String PLAYER_PASS = "1qwerty2";
    private static final String AUTH_TAG = "Authorization";
    private static final String BEARER_TAG = "Bearer ";
    @Autowired
    public TestRestTemplate restTemplate;
    public MultiValueMap<String, String> jwt;

    @BeforeAll
    void initTestProperty(@Autowired AuthenticationService authService,
                          @Autowired PlayerService playerService) {
        PlayerRequestDto dto = new PlayerRequestDto(PLAYER_LOGIN, PLAYER_PASS);
        playerService.save(dto);
        jwt = new LinkedMultiValueMap<>();
        jwt.put(AUTH_TAG, List.of(BEARER_TAG + authService.login(dto).getToken()));
        User player = new User(PLAYER_LOGIN, PLAYER_PASS, List.of());
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(player, "", List.of()));
    }

    @AfterAll
    @CacheEvict(value = "player", allEntries = true)
    void clearDBAfterTests(@Autowired PlayerRepository repository) {
        repository.deleteAll();
    }
}
