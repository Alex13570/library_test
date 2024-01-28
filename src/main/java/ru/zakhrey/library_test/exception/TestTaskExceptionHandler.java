package ru.zakhrey.library_test.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.zakhrey.library_test.dto.utils.ErrorResponse;

@ControllerAdvice
public class TestTaskExceptionHandler {

    @ExceptionHandler(TestTaskException.class)
    public ResponseEntity<ErrorResponse> handleException(TestTaskException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(
                ErrorResponse.builder().status(exception.getHttpStatus()).message(exception.getMessage()).build()
        );
    }
}