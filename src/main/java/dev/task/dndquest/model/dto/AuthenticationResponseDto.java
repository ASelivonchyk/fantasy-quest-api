package dev.task.dndquest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String token;
    private HttpStatusCode status;
}
