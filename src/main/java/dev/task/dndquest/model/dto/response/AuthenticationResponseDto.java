package dev.task.dndquest.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String token;
    private HttpStatusCode status;
}
