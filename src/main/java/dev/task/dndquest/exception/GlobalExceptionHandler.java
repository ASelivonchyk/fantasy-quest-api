package dev.task.dndquest.exception;

import dev.task.dndquest.model.dto.ExceptionResponseDto;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(ex.getAllErrors().stream()
                                       .map(e -> new ExceptionResponseDto(
                                               e.getDefaultMessage(),
                                               HttpStatus.PRECONDITION_FAILED))
                                       .distinct()
                                       .collect(Collectors.toList()));
    }

    @ExceptionHandler({CharNotFoundException.class, RaceNotFoundException.class,
            ItemNotFoundException.class, ClassNotFoundException.class})
    public ResponseEntity<Object> handleDBNotFoundExceptions(
            Exception ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ExceptionResponseDto(
                        ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE));
    }

    @ExceptionHandler({BadCredentialsException.class, JwtAuthenticationException.class,
            DuplicateLoginException.class})
    public ResponseEntity<Object> handleAuthenticationsExceptions(
            Exception ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDto(
                        ex.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
