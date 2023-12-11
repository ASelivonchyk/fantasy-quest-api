package dev.task.dndquest.exception;

import java.util.HashMap;
import java.util.Map;
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
    private static final String MESSAGE_TAG = "message";
    private static final String STATUS_TAG = "status";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(ex.getAllErrors()
                        .stream()
                        .map(e -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put(STATUS_TAG, status.toString());
                            map.put(MESSAGE_TAG, e.getDefaultMessage());
                            return map;})
                        .collect(Collectors.toList()));
    }

    @ExceptionHandler(DBException.class)
    public ResponseEntity<Object> handleDBException(
            DBException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(MESSAGE_TAG, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(body);
    }
}
