package dev.task.dndquest.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ExceptionResponseDto {
    private String message;
    private HttpStatusCode status;
}
