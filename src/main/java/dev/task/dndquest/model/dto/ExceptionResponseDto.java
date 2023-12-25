package dev.task.dndquest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ExceptionResponseDto {
    private String message;
    private HttpStatusCode status;
}
