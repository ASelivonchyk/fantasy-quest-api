package dev.task.dndquest.exception;

import dev.task.dndquest.model.dto.ExceptionRequestDto;
import java.util.stream.Collectors;
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
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(ex.getAllErrors().stream()
                                       .map(e -> new ExceptionRequestDto(
                                               e.getDefaultMessage(),
                                               HttpStatus.PRECONDITION_FAILED))
                                       .distinct()
                                       .collect(Collectors.toList()));
    }

    @ExceptionHandler({CharNotFoundException.class, RaceNotFoundException.class})
    public ResponseEntity<Object> handleDBNotFoundExceptions(
            CharNotFoundException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ExceptionRequestDto(
                        ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE));
    }
}
