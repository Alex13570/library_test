package ru.zakhrey.library_test.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TestTaskException extends Exception {

    private final HttpStatus httpStatus;

    public TestTaskException(String title, HttpStatus status) {
        super(title);
        httpStatus = status;
    }
}
