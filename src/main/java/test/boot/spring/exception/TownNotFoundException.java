package test.boot.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TownNotFoundException extends RuntimeException {
    public TownNotFoundException(String message) {
        super(message);
    }
}
