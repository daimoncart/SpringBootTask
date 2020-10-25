package test.boot.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmplyeeNotFoundException extends RuntimeException {
    public EmplyeeNotFoundException(String message) {
        super(message);
    }
}
