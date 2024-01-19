package dev.task.dndquest.exception;

import dev.task.dndquest.model.dto.response.ExceptionResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.ConstraintViolationException;
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
                                               e.getDefaultMessage()))
                                       .distinct()
                                       .collect(Collectors.toList()));
    }

    @ExceptionHandler (ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex) {
        List<ExceptionResponseDto> list = Arrays.stream(ex.getMessage().split(","))
                .map(m -> String.format("in position %s %s",
                        m.substring(m.indexOf('[') + 1, m.indexOf(']')),
                        m.substring(m.indexOf(':') + 1)))
                .map(ExceptionResponseDto::new)
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(list);
    }

    @ExceptionHandler({CharNotFoundException.class, RaceNotFoundException.class,
            ItemNotFoundException.class, ClassNotFoundException.class, StoryLineNotFoundException.class})
    public ResponseEntity<Object> handleDBNotFoundExceptions(
            Exception ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ExceptionResponseDto(
                        ex.getMessage()));
    }

    @ExceptionHandler({BadCredentialsException.class, JwtAuthenticationException.class,
            DuplicateLoginException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleAuthenticationsExceptions(
            Exception ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDto(
                        ex.getMessage()));
    }
}
